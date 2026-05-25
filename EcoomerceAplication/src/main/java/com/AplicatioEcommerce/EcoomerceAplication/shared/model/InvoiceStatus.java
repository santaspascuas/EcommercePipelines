package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

public enum InvoiceStatus {
    DRAFT,       // borrador — no tiene número, editable
    ISSUED,      // emitida — número asignado, no editable
    SENT,        // enviada al cliente
    PAID,        // pagada
    OVERDUE,     // vencida sin pagar
    CANCELLED,   // cancelada
    RECTIFIED    // tiene factura rectificativa asociada
}
