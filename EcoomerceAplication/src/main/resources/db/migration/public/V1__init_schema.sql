CREATE SEQUENCE IF NOT EXISTS customer_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS profile_id_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS public.customers (
    customer_id BIGSERIAL PRIMARY KEY,

    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,
    account_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
    failed_attempts INT NOT NULL DEFAULT 0,
    account_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    credentials_non_expired BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    lock_time TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_customers_email ON public.customers(email);

CREATE TABLE IF NOT EXISTS public.tenants (
    tenant_id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL UNIQUE 
        REFERENCES public.customers(customer_id) ON DELETE CASCADE,

    tenant_code VARCHAR(100) UNIQUE NOT NULL,
    schema_name VARCHAR(100) UNIQUE NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_tenants_code ON public.tenants(tenant_code);

CREATE TABLE IF NOT EXISTS public.customer_profiles (
    profile_id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL UNIQUE 
        REFERENCES public.customers(customer_id) ON DELETE CASCADE,

    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(50),
    avatar_url TEXT
);

CREATE TABLE IF NOT EXISTS public.addresses (
    address_id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL 
        REFERENCES public.customers(customer_id) ON DELETE CASCADE,

    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(20),
    country VARCHAR(100)
);

CREATE INDEX IF NOT EXISTS idx_addresses_customer 
    ON public.addresses(customer_id);

