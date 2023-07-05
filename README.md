# product-inventory-service


#PostGreSQL DB Script
-- Table: public.product

-- DROP TABLE IF EXISTS public.product;

CREATE TABLE IF NOT EXISTS public.product
(
product_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
name character varying(100) COLLATE pg_catalog."default" NOT NULL,
description character varying COLLATE pg_catalog."default" NOT NULL,
product_type character varying(100) COLLATE pg_catalog."default" NOT NULL,
quantity bigint NOT NULL,
price double precision NOT NULL,
CONSTRAINT pk_product_id PRIMARY KEY (product_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
OWNER to postgres;

