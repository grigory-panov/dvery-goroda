--
---
 -- PostgreSQL database dump
 --

 -- Dumped from database version 9.3.5
 -- Dumped by pg_dump version 9.3.5
 -- Started on 2014-10-18 22:37:42 MSK

 SET statement_timeout = 0;
 SET lock_timeout = 0;
 SET client_encoding = 'UTF8';
 SET standard_conforming_strings = on;
 SET check_function_bodies = false;
 SET client_min_messages = warning;

 --
 -- TOC entry 181 (class 3079 OID 12670)
 -- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
 --

 CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


 --
 -- TOC entry 2919 (class 0 OID 0)
 -- Dependencies: 181
 -- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
 --

 COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


 SET search_path = public, pg_catalog;

 SET default_with_oids = false;

 --
 -- TOC entry 171 (class 1259 OID 16388)
 -- Name: category; Type: TABLE; Schema: public; Owner: door
 --

 CREATE TABLE category (
     id bigint NOT NULL,
     name character varying(50),
     description character varying(1024),
     deleted boolean DEFAULT false NOT NULL,
     "group" integer,
     "order" integer
 );


 ALTER TABLE public.category OWNER TO door;

 --
 -- TOC entry 170 (class 1259 OID 16386)
 -- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: door
 --

 CREATE SEQUENCE category_id_seq
     START WITH 1
     INCREMENT BY 1
     NO MINVALUE
     NO MAXVALUE
     CACHE 1;


 ALTER TABLE public.category_id_seq OWNER TO door;

 --
 -- TOC entry 2920 (class 0 OID 0)
 -- Dependencies: 170
 -- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: door
 --

 ALTER SEQUENCE category_id_seq OWNED BY category.id;


 --
 -- TOC entry 173 (class 1259 OID 16403)
 -- Name: feedback; Type: TABLE; Schema: public; Owner: door
 --

 CREATE TABLE feedback (
     id bigint NOT NULL,
     author character varying(256) NOT NULL,
     date_add timestamp without time zone NOT NULL,
     text text,
     approved boolean DEFAULT false NOT NULL,
     deleted boolean DEFAULT false NOT NULL,
     ip character varying(128),
     date_approve timestamp without time zone,
     date_delete timestamp without time zone,
     country character varying,
     city character varying
 );


 ALTER TABLE public.feedback OWNER TO door;

 --
 -- TOC entry 172 (class 1259 OID 16401)
 -- Name: feedback_id_seq; Type: SEQUENCE; Schema: public; Owner: door
 --

 CREATE SEQUENCE feedback_id_seq
     START WITH 1
     INCREMENT BY 1
     NO MINVALUE
     NO MAXVALUE
     CACHE 1;


 ALTER TABLE public.feedback_id_seq OWNER TO door;

 --
 -- TOC entry 2921 (class 0 OID 0)
 -- Dependencies: 172
 -- Name: feedback_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: door
 --

 ALTER SEQUENCE feedback_id_seq OWNED BY feedback.id;


 --
 -- TOC entry 175 (class 1259 OID 16416)
 -- Name: info; Type: TABLE; Schema: public; Owner: door
 --

 CREATE TABLE info (
     id bigint NOT NULL,
     header character varying(256),
     body text,
     date_add timestamp without time zone NOT NULL,
     deleted boolean DEFAULT false NOT NULL
 );


 ALTER TABLE public.info OWNER TO door;

 --
 -- TOC entry 174 (class 1259 OID 16414)
 -- Name: info_id_seq; Type: SEQUENCE; Schema: public; Owner: door
 --

 CREATE SEQUENCE info_id_seq
     START WITH 1
     INCREMENT BY 1
     NO MINVALUE
     NO MAXVALUE
     CACHE 1;


 ALTER TABLE public.info_id_seq OWNER TO door;

 --
 -- TOC entry 2922 (class 0 OID 0)
 -- Dependencies: 174
 -- Name: info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: door
 --

 ALTER SEQUENCE info_id_seq OWNED BY info.id;


 --
 -- TOC entry 177 (class 1259 OID 16428)
 -- Name: product; Type: TABLE; Schema: public; Owner: door
 --

 CREATE TABLE product (
     id bigint NOT NULL,
     name character varying(128) NOT NULL,
     description character varying(1024),
     category_id bigint NOT NULL,
     date_add timestamp without time zone NOT NULL,
     date_delete timestamp without time zone,
     date_update timestamp without time zone,
     deleted boolean DEFAULT false NOT NULL
 );


 ALTER TABLE public.product OWNER TO door;

 --
 -- TOC entry 176 (class 1259 OID 16426)
 -- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: door
 --

 CREATE SEQUENCE product_id_seq
     START WITH 1
     INCREMENT BY 1
     NO MINVALUE
     NO MAXVALUE
     CACHE 1;


 ALTER TABLE public.product_id_seq OWNER TO door;

 --
 -- TOC entry 2923 (class 0 OID 0)
 -- Dependencies: 176
 -- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: door
 --

 ALTER SEQUENCE product_id_seq OWNED BY product.id;


 --
 -- TOC entry 179 (class 1259 OID 16441)
 -- Name: product_version; Type: TABLE; Schema: public; Owner: door
 --

 CREATE TABLE product_version (
     id bigint NOT NULL,
     product_id bigint NOT NULL,
     price numeric(10,2) NOT NULL,
     name character varying(128),
     description character varying(512),
     size character varying(128),
     deleted boolean DEFAULT false NOT NULL,
     date_add timestamp without time zone NOT NULL,
     date_delete timestamp without time zone,
     date_update timestamp without time zone,
     "order" integer
 );


 ALTER TABLE public.product_version OWNER TO door;

 --
 -- TOC entry 178 (class 1259 OID 16439)
 -- Name: product_version_id_seq; Type: SEQUENCE; Schema: public; Owner: door
 --

 CREATE SEQUENCE product_version_id_seq
     START WITH 1
     INCREMENT BY 1
     NO MINVALUE
     NO MAXVALUE
     CACHE 1;


 ALTER TABLE public.product_version_id_seq OWNER TO door;

 --
 -- TOC entry 2924 (class 0 OID 0)
 -- Dependencies: 178
 -- Name: product_version_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: door
 --

 ALTER SEQUENCE product_version_id_seq OWNED BY product_version.id;


 --
 -- TOC entry 180 (class 1259 OID 24587)
 -- Name: settings; Type: TABLE; Schema: public; Owner: door
 --

 CREATE TABLE settings (
     key character varying(100) NOT NULL,
     value character varying(255),
     description character varying(1024)
 );


 ALTER TABLE public.settings OWNER TO door;

 --
 -- TOC entry 2776 (class 2604 OID 16391)
 -- Name: id; Type: DEFAULT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY category ALTER COLUMN id SET DEFAULT nextval('category_id_seq'::regclass);


 --
 -- TOC entry 2778 (class 2604 OID 16406)
 -- Name: id; Type: DEFAULT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY feedback ALTER COLUMN id SET DEFAULT nextval('feedback_id_seq'::regclass);


 --
 -- TOC entry 2781 (class 2604 OID 16419)
 -- Name: id; Type: DEFAULT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY info ALTER COLUMN id SET DEFAULT nextval('info_id_seq'::regclass);


 --
 -- TOC entry 2783 (class 2604 OID 16431)
 -- Name: id; Type: DEFAULT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


 --
 -- TOC entry 2785 (class 2604 OID 16444)
 -- Name: id; Type: DEFAULT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY product_version ALTER COLUMN id SET DEFAULT nextval('product_version_id_seq'::regclass);


 --
 -- TOC entry 2788 (class 2606 OID 16397)
 -- Name: category_PK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY category
     ADD CONSTRAINT "category_PK" PRIMARY KEY (id);


 --
 -- TOC entry 2791 (class 2606 OID 16399)
 -- Name: category_name_UK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY category
     ADD CONSTRAINT "category_name_UK" UNIQUE (name);


 --
 -- TOC entry 2793 (class 2606 OID 16413)
 -- Name: feedback_PK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY feedback
     ADD CONSTRAINT "feedback_PK" PRIMARY KEY (id);


 --
 -- TOC entry 2795 (class 2606 OID 16425)
 -- Name: info_PK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY info
     ADD CONSTRAINT "info_PK" PRIMARY KEY (id);


 --
 -- TOC entry 2803 (class 2606 OID 24594)
 -- Name: pk_settings; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY settings
     ADD CONSTRAINT pk_settings PRIMARY KEY (key);


 --
 -- TOC entry 2797 (class 2606 OID 16436)
 -- Name: product_PK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY product
     ADD CONSTRAINT "product_PK" PRIMARY KEY (id);


 --
 -- TOC entry 2799 (class 2606 OID 16438)
 -- Name: product_category_name_UK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY product
     ADD CONSTRAINT "product_category_name_UK" UNIQUE (name, category_id);


 --
 -- TOC entry 2801 (class 2606 OID 16450)
 -- Name: product_version_PK; Type: CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY product_version
     ADD CONSTRAINT "product_version_PK" PRIMARY KEY (id);


 --
 -- TOC entry 2789 (class 1259 OID 16400)
 -- Name: category_deleted_IDX; Type: INDEX; Schema: public; Owner: door
 --

 CREATE INDEX "category_deleted_IDX" ON category USING btree (deleted);


 --
 -- TOC entry 2804 (class 2606 OID 16451)
 -- Name: prodversion_prod_id_FK; Type: FK CONSTRAINT; Schema: public; Owner: door
 --

 ALTER TABLE ONLY product_version
     ADD CONSTRAINT "prodversion_prod_id_FK" FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT;


 --
 -- TOC entry 2918 (class 0 OID 0)
 -- Dependencies: 5
 -- Name: public; Type: ACL; Schema: -; Owner: postgres
 --

 REVOKE ALL ON SCHEMA public FROM PUBLIC;
 REVOKE ALL ON SCHEMA public FROM postgres;
 GRANT ALL ON SCHEMA public TO postgres;
 GRANT ALL ON SCHEMA public TO PUBLIC;


 -- Completed on 2014-10-18 22:37:42 MSK

 --
 -- PostgreSQL database dump complete
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-10-18 22:33:06 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SELECT pg_catalog.setval('category_id_seq', 1, true);
SELECT pg_catalog.setval('feedback_id_seq', 1, true);
SELECT pg_catalog.setval('info_id_seq', 1, true);


SELECT pg_catalog.setval('product_id_seq', 1, true);

SELECT pg_catalog.setval('product_version_id_seq', 1, true);

INSERT INTO settings (key, value, description) VALUES ('storage_dir', '/usr/local/image', 'Директория для хранения изображений');
INSERT INTO settings (key, value, description) VALUES ('contacts_address', '11111, г Цошкар-Ола, ул садовая, д 1', 'Адрес организации');
INSERT INTO settings (key, value, description) VALUES ('contacts_phone', '+79099715497', 'Телефон организации');
INSERT INTO settings (key, value, description) VALUES ('contacts_email', 'email@example.com', 'Email организации');
INSERT INTO settings (key, value, description) VALUES ('contacts_lat', '56.619331', 'Широта ');
INSERT INTO settings (key, value, description) VALUES ('contacts_lng', '47.836851', 'Долгота');
INSERT INTO settings (key, value, description) VALUES ('project_name', 'Двери города', 'Название проекта');
INSERT INTO settings (key, value, description) VALUES ('author', 'grigory.panov@gmal.com', 'Ссылка на автора, в заголовке');
INSERT INTO settings (key, value, description) VALUES ('copyright', 'Grigory Panov, 2014', 'Ссылка на автора внизу страницы');
INSERT INTO settings (key, value, description) VALUES ('contacts_organization', 'ООО "Организация1"', 'Название организации');
INSERT INTO settings (key, value, description) VALUES ('description', 'Двери, Деревянные двери, купить двери в Йошкар-Оле', 'Описание проекта');
INSERT INTO settings (key, value, description) VALUES ('feedbacks_per_page', '10', 'Количество отзывов на одной странице');
INSERT INTO settings (key, value, description) VALUES ('products_per_page', '3', 'Количество товаров на одной странице');


-- Completed on 2014-10-18 22:33:06 MSK

--
-- PostgreSQL database dump complete
--


-- Completed on 2014-09-27 17:07:24 MSK

--
-- PostgreSQL database dump complete
--

