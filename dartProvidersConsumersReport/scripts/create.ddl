CREATE DATABASE providers_and_consumers
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;
       
CREATE TABLE address
(
  id bigint NOT NULL,
  region character varying(90),
  town character varying(60),
  street character varying(120),
  "houseNumber" integer,
  CONSTRAINT "Address_pkey" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE address
  OWNER TO postgres;
  
CREATE TABLE product
(
  id bigint NOT NULL,
  title character varying(120),
  CONSTRAINT "Product_pkey" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE product
  OWNER TO postgres;  
  
CREATE TABLE consumer
(
  id bigint NOT NULL,
  addressid bigint NOT NULL,
  title character varying(120),
  CONSTRAINT "Consumer_pkey" PRIMARY KEY (id),
  CONSTRAINT "Consumer_addressid_fkey" FOREIGN KEY (addressid)
      REFERENCES address (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE consumer
  OWNER TO postgres;

CREATE TABLE producer
(
  id bigint NOT NULL,
  addressid bigint NOT NULL,
  title character varying(120),
  CONSTRAINT "Producer_pkey" PRIMARY KEY (id),
  CONSTRAINT "Producer_addressid_fkey" FOREIGN KEY (addressid)
      REFERENCES address (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE producer
  OWNER TO postgres;
  
 CREATE TABLE needs
(
  id bigint NOT NULL,
  count integer,
  productid bigint NOT NULL,
  consumerid bigint NOT NULL,
  CONSTRAINT "Needs_pkey" PRIMARY KEY (id),
  CONSTRAINT "Needs_consumerid_fkey" FOREIGN KEY (consumerid)
      REFERENCES consumer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Needs_productid_fkey" FOREIGN KEY (productid)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE needs
  OWNER TO postgres;
  
CREATE TABLE offerts
(
  id bigint NOT NULL,
  count integer,
  productid bigint NOT NULL,
  producerid bigint NOT NULL,
  CONSTRAINT "Offerts_pkey" PRIMARY KEY (id),
  CONSTRAINT "Offerts_producerid_fkey" FOREIGN KEY (producerid)
      REFERENCES producer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Offerts_productid_fkey" FOREIGN KEY (productid)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE offerts
  OWNER TO postgres;  
  
  