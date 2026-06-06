package com.AplicatioEcommerce.EcoomerceAplication.module.billing.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceItemRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ClientNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.InvoiceException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.InvoiceNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ServiceException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.security.TenantContextHolder;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ProductException;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.mapper.InvoiceMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Address;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Client;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Invoice;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceItem;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatus;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.AddressDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.ClientDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.CustomerDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository.InvoiceDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.ProductDao;

@Service
public class InvoiceServiceImplements implements InvoiceService {

    private final InvoiceDao invoiceDao;
    private final CustomerDao customerDao;
    private final ClientDao clientDao;
    private final AddressDao addressDao;
    private final ProductDao productDao;
    private final InvoiceStatusService statusService;
    private final InvoiceNumberGenerator numberGenerator;

    private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImplements.class);

    public InvoiceServiceImplements(InvoiceDao invoiceDao, CustomerDao customerDao,
            ClientDao clientDao, AddressDao addressDao, ProductDao productDao,
            InvoiceStatusService statusService, InvoiceNumberGenerator numberGenerator) {
        this.invoiceDao = invoiceDao;
        this.customerDao = customerDao;
        this.clientDao = clientDao;
        this.addressDao = addressDao;
        this.productDao = productDao;
        this.statusService = statusService;
        this.numberGenerator = numberGenerator;
    }

    @Override
    @Transactional
    public InvoiceResponseDTO createDraft(InvoiceRequestDTO dto) {
        log.info("[createDraft] customerId={}, clientId={}", dto.getCustomerId(), dto.getClientId());

        Customer customer = customerDao.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("id: " + dto.getCustomerId()));

        Client client = clientDao.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("id: " + dto.getClientId()));

        Address address = null;
        if (dto.getAddressId() != null) {
            address = addressDao.findById(dto.getAddressId()).orElse(null);
        }

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setClient(client);
        invoice.setAddress(address);
        invoice.setSeries(dto.getSeries() != null ? dto.getSeries() : "F");
        invoice.setIssueDate(dto.getIssueDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setPaymentMethod(dto.getPaymentMethod());
        invoice.setNotes(dto.getNotes());
        invoice.setStatus(InvoiceStatus.DRAFT);

        List<InvoiceItem> items = buildItems(invoice, dto.getItems());
        invoice.setItems(items);
        recalculateTotals(invoice);

        Invoice saved = invoiceDao.save(invoice);
        statusService.addStatus(saved.getInvoiceId(), InvoiceStatus.DRAFT);

        log.info("[createDraft] Fin OK - invoiceId={}", saved.getInvoiceId());
        return InvoiceMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public InvoiceResponseDTO issue(Long invoiceId) {
        log.info("[issue] invoiceId={}", invoiceId);

        Invoice invoice = invoiceDao.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("id: " + invoiceId));

        checkOwnership(invoice.getCustomer().getId());

        if (invoice.getStatus() != InvoiceStatus.DRAFT) {
            throw new InvoiceException(GlobalErrorCodeConstants.INVOICE_CANNOT_EDIT_ISSUED,
                    "Solo se pueden emitir borradores");
        }

        String number = numberGenerator.next(invoice.getCustomer(), invoice.getSeries());
        invoice.setInvoiceNumber(number);
        invoice.setStatus(InvoiceStatus.ISSUED);
        invoiceDao.save(invoice);
        statusService.addStatus(invoiceId, InvoiceStatus.ISSUED);

        log.info("[issue] Factura emitida con número {}", number);
        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponseDTO getById(Long invoiceId) {
        log.info("[getById] id={}", invoiceId);
        Invoice invoice = invoiceDao.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("id: " + invoiceId));
        checkOwnership(invoice.getCustomer().getId());
        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<InvoiceResponseDTO> getInvoicesPage(Long customerId, PageParam pageParam) {
        log.info("[getInvoicesPage] customerId={}", customerId);
        return new PageResult<>(
                invoiceDao.findByCustomerId(customerId, pageParam.toPageable())
                        .map(InvoiceMapper::toResponse)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<InvoiceResponseDTO> getInvoicesByStatus(Long customerId, InvoiceStatus status, PageParam pageParam) {
        log.info("[getInvoicesByStatus] customerId={}, status={}", customerId, status);
        return new PageResult<>(
                invoiceDao.findByCustomerIdAndStatus(customerId, status, pageParam.toPageable())
                        .map(InvoiceMapper::toResponse)
        );
    }

    @Override
    @Transactional
    public InvoiceResponseDTO updateStatus(Long invoiceId, InvoiceStatus status) {
        log.info("[updateStatus] invoiceId={}, status={}", invoiceId, status);
        Invoice invoice = invoiceDao.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("id: " + invoiceId));

        checkOwnership(invoice.getCustomer().getId());

        if (invoice.getStatus() == InvoiceStatus.CANCELLED) {
            throw new InvoiceException(GlobalErrorCodeConstants.INVOICE_ALREADY_CANCELLED);
        }

        invoice.setStatus(status);
        invoiceDao.save(invoice);
        statusService.addStatus(invoiceId, status);
        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    @Transactional
    public void cancel(Long invoiceId) {
        log.info("[cancel] invoiceId={}", invoiceId);
        Invoice invoice = invoiceDao.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("id: " + invoiceId));

        checkOwnership(invoice.getCustomer().getId());

        if (invoice.getStatus() == InvoiceStatus.PAID) {
            throw new InvoiceException(GlobalErrorCodeConstants.INVOICE_CANNOT_EDIT_ISSUED,
                    "No se puede cancelar una factura ya pagada");
        }

        invoice.setStatus(InvoiceStatus.CANCELLED);
        invoiceDao.save(invoice);
        statusService.addStatus(invoiceId, InvoiceStatus.CANCELLED);
        log.info("[cancel] Factura {} cancelada", invoiceId);
    }

    private void checkOwnership(Long ownerCustomerId) {
        Long tenantId = TenantContextHolder.getCustomerId();
        if (tenantId != null && !tenantId.equals(ownerCustomerId)) {
            throw new ServiceException(GlobalErrorCodeConstants.FORBIDDEN);
        }
    }

    private List<InvoiceItem> buildItems(Invoice invoice, List<InvoiceItemRequestDTO> itemDtos) {
        List<InvoiceItem> items = new ArrayList<>();
        for (InvoiceItemRequestDTO dto : itemDtos) {
            Product product = productDao.findById(dto.getProductId())
                    .orElseThrow(() -> new ProductException("id: " + dto.getProductId()));

            InvoiceItem item = new InvoiceItem();
            item.setInvoice(invoice);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(product.getDefaultPrice());

            BigDecimal base = product.getDefaultPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
            item.setSubtotal(base);
            items.add(item);
        }
        return items;
    }

    private void recalculateTotals(Invoice invoice) {
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        BigDecimal irpfAmount = BigDecimal.ZERO;

        for (InvoiceItem item : invoice.getItems()) {
            BigDecimal base = item.getSubtotal();
            subtotal = subtotal.add(base);
            taxAmount = taxAmount.add(base.multiply(item.getTaxRate()).divide(BigDecimal.valueOf(100)));
            irpfAmount = irpfAmount.add(base.multiply(item.getIrpf()).divide(BigDecimal.valueOf(100)));
        }

        invoice.setSubtotal(subtotal);
        invoice.setTaxAmount(taxAmount);
        invoice.setIrpfAmount(irpfAmount);
        invoice.setTotal(subtotal.add(taxAmount).subtract(irpfAmount));
    }
}
