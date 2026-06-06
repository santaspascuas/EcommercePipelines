package com.AplicatioEcommerce.EcoomerceAplication.module.billing.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.InvoiceException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.InvoiceNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Invoice;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatus;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatusHistory;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository.InvoiceDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository.InvoiceStatusDao;

@Service
public class InvoiceStatusServiceImplements implements InvoiceStatusService {

    private final InvoiceStatusDao statusDao;
    private final InvoiceDao invoiceDao;
    private static final Logger log = LoggerFactory.getLogger(InvoiceStatusServiceImplements.class);

    public InvoiceStatusServiceImplements(InvoiceStatusDao statusDao, InvoiceDao invoiceDao) {
        this.statusDao = statusDao;
        this.invoiceDao = invoiceDao;
    }

    @Override
    @Transactional
    public InvoiceStatusHistory addStatus(Long invoiceId, InvoiceStatus status) {
        log.info("[addStatus] invoiceId={}, status={}", invoiceId, status);
        Invoice invoice = invoiceDao.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("id: " + invoiceId));
        InvoiceStatusHistory history = new InvoiceStatusHistory();
        history.setInvoice(invoice);
        history.setStatus(status);
        history.setTimestamp(LocalDateTime.now());
        return statusDao.save(history);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceStatusHistory> getHistory(Long invoiceId) {
        log.info("[getHistory] invoiceId={}", invoiceId);
        if (!invoiceDao.existsById(invoiceId)) {
            throw new InvoiceNotFoundException("id: " + invoiceId);
        }
        return statusDao.findByInvoiceInvoiceIdOrderByTimestampAsc(invoiceId);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceStatusHistory getLastStatus(Long invoiceId) {
        log.info("[getLastStatus] invoiceId={}", invoiceId);
        if (!invoiceDao.existsById(invoiceId)) {
            throw new InvoiceNotFoundException("id: " + invoiceId);
        }
        InvoiceStatusHistory last = statusDao.findTopByInvoiceInvoiceIdOrderByTimestampDesc(invoiceId);
        if (last == null) {
            throw new InvoiceException(GlobalErrorCodeConstants.INVOICE_INVALID_STATUS_TRANSITION,
                    "Sin historial para la factura " + invoiceId);
        }
        return last;
    }
}
