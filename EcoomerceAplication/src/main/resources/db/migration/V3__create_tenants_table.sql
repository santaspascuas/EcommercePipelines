-- ============================================================
-- V3: Tabla tenants (1-1 con customers)
-- Desacopla el tenant_code/schema_name del customer
-- ============================================================

CREATE TABLE public.tenants (
    customer_id BIGINT PRIMARY KEY REFERENCES public.customers(customer_id),
    tenant_code VARCHAR(36) NOT NULL,
    schema_name VARCHAR(63) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT uq_tenants_tenant_code UNIQUE (tenant_code),
    CONSTRAINT uq_tenants_schema_name UNIQUE (schema_name)
);

-- Migramos los tenant_code existentes desde customers
INSERT INTO public.tenants (customer_id, tenant_code, schema_name, created_at)
SELECT customer_id, tenant_code, 'tenant_' || replace(tenant_code, '-', ''), now()
FROM public.customers;

-- Eliminamos la columna y constraint antiguos de customers
ALTER TABLE public.customers DROP CONSTRAINT uq_customers_tenant_code;
ALTER TABLE public.customers DROP COLUMN tenant_code;
