-- ============================================================
-- V2: Identificador de tenant independiente del customer_id
-- ============================================================

ALTER TABLE public.customers ADD COLUMN tenant_code VARCHAR(36);

UPDATE public.customers SET tenant_code = gen_random_uuid()::text WHERE tenant_code IS NULL;

ALTER TABLE public.customers ALTER COLUMN tenant_code SET NOT NULL;

ALTER TABLE public.customers ADD CONSTRAINT uq_customers_tenant_code UNIQUE (tenant_code);
