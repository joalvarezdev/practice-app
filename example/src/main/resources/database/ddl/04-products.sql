CREATE TABLE IF NOT EXISTS products (
    id SERIAL NOT NULL,
    sku VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT "pk_products" PRIMARY KEY (id)
);