--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.19
-- Dumped by pg_dump version 10.4

-- Started on 2018-12-26 19:33:43 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

-- DROP DATABASE snacks_applaudo;
--
-- TOC entry 2063 (class 1262 OID 24848)
-- Name: snacks_applaudo; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE snacks_applaudo WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


\connect snacks_applaudo

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 11861)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 24849)
-- Name: account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(100) NOT NULL,
    adminrole boolean NOT NULL
);


--
-- TOC entry 174 (class 1259 OID 24852)
-- Name: like; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."like" (
    userid integer NOT NULL,
    productid integer NOT NULL,
    id integer NOT NULL
);

--
-- TOC entry 175 (class 1259 OID 24855)
-- Name: like_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.like_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 181 (class 1259 OID 24937)
-- Name: price_update; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.price_update (
    id integer NOT NULL,
    accountid integer NOT NULL,
    productid integer NOT NULL,
    oldprice numeric(16,2) NOT NULL,
    newprice numeric(16,2) NOT NULL,
    "timestamp" timestamp without time zone NOT NULL
);


--
-- TOC entry 182 (class 1259 OID 24952)
-- Name: price_update_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.price_update_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 176 (class 1259 OID 24857)
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    name character varying NOT NULL,
    stock integer DEFAULT 0 NOT NULL,
    price numeric(16,2) DEFAULT 0.0 NOT NULL,
    likes integer DEFAULT 0 NOT NULL
);


--
-- TOC entry 177 (class 1259 OID 24866)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 178 (class 1259 OID 24868)
-- Name: purchase; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.purchase (
    id bigint NOT NULL,
    accountid bigint NOT NULL,
    productid bigint NOT NULL,
    quantity bigint NOT NULL,
    "timestamp" date
);


--
-- TOC entry 179 (class 1259 OID 24871)
-- Name: purchase_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 180 (class 1259 OID 24873)
-- Name: token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.token (
    id uuid NOT NULL,
    userid bigint
);


--
-- TOC entry 2048 (class 0 OID 24849)
-- Dependencies: 173
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.account VALUES (1, 'mjovel', 'mjovel', true);
INSERT INTO public.account VALUES (3, 'root', 'toor', true);
INSERT INTO public.account VALUES (2, 'rafa1337', 'rafa1337', false);
INSERT INTO public.account VALUES (4, 'c1', 'test', false);
INSERT INTO public.account VALUES (5, 'c2', 'test', false);
INSERT INTO public.account VALUES (6, 'c3', 'test', false);
INSERT INTO public.account VALUES (7, 'c4', 'test', false);


--
-- TOC entry 2049 (class 0 OID 24852)
-- Dependencies: 174
-- Data for Name: like; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."like" VALUES (2, 11, 2);
INSERT INTO public."like" VALUES (2, 1, 3);


--
-- TOC entry 2056 (class 0 OID 24937)
-- Dependencies: 181
-- Data for Name: price_update; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.price_update VALUES (1, 1, 1, 100.10, 1.30, '2018-12-04 10:05:14.847');
INSERT INTO public.price_update VALUES (3, 1, 1, 1.30, 1.30, '2018-12-04 10:08:27.547');
INSERT INTO public.price_update VALUES (4, 1, 1, 1.30, 1.20, '2018-12-04 10:24:19.507');


--
-- TOC entry 2051 (class 0 OID 24857)
-- Dependencies: 176
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product VALUES (2, 'snack uca', 100, 100.10, 0);
INSERT INTO public.product VALUES (4, 'boliqueso', 10, 0.25, 0);
INSERT INTO public.product VALUES (3, 'doritos', 10, 0.25, 0);
INSERT INTO public.product VALUES (6, 'palomitas', 10, 0.25, 0);
INSERT INTO public.product VALUES (5, 'churro diana', 10, 0.25, 0);
INSERT INTO public.product VALUES (8, 'chori', 10, 0.25, 0);
INSERT INTO public.product VALUES (7, 'dedo de queso', 10, 0.25, 0);
INSERT INTO public.product VALUES (10, 'pupusa', 10, 0.25, 0);
INSERT INTO public.product VALUES (9, 'mataniño', 10, 0.25, 0);
INSERT INTO public.product VALUES (11, 'atol de elote', 8, 0.50, 1);
INSERT INTO public.product VALUES (13, 'atol de maiz tostado', 10, 0.50, 0);
INSERT INTO public.product VALUES (1, 'snack applaudo', 96, 1.20, 2);


--
-- TOC entry 2053 (class 0 OID 24868)
-- Dependencies: 178
-- Data for Name: purchase; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.purchase VALUES (2, 2, 11, 2, '2018-12-04');
INSERT INTO public.purchase VALUES (3, 2, 1, 1, '2018-12-04');
INSERT INTO public.purchase VALUES (4, 2, 1, 1, '2018-12-04');


--
-- TOC entry 2055 (class 0 OID 24873)
-- Dependencies: 180
-- Data for Name: token; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.token VALUES ('471b55c5-2fdc-488b-b81d-ea2d9ef7f968', 1);
INSERT INTO public.token VALUES ('3b7654c3-7c64-4c80-b9ed-ac9006089d50', 2);
INSERT INTO public.token VALUES ('87ef21bc-1f68-4b56-81fc-18877fa5b5a9', 1);
INSERT INTO public.token VALUES ('c3c34264-2f6f-4632-9b2b-fccecd23d9c7', 1);
INSERT INTO public.token VALUES ('696b3458-3038-4e28-a70b-57708ab04176', 1);
INSERT INTO public.token VALUES ('eb3327ed-e5dc-4df8-a7e0-ab90df9f1235', 1);
INSERT INTO public.token VALUES ('f89d220b-e5d6-42f6-84c8-9906a0063364', 2);


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 175
-- Name: like_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.like_id_seq', 3, true);


--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 182
-- Name: price_update_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.price_update_id_seq', 4, true);


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 177
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_id_seq', 13, true);


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 179
-- Name: purchase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.purchase_id_seq', 4, true);


--
-- TOC entry 1917 (class 2606 OID 24877)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 1919 (class 2606 OID 24879)
-- Name: like like_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."like"
    ADD CONSTRAINT like_pk PRIMARY KEY (id);


--
-- TOC entry 1921 (class 2606 OID 24881)
-- Name: like like_tuple; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."like"
    ADD CONSTRAINT like_tuple UNIQUE (userid, productid);


--
-- TOC entry 1931 (class 2606 OID 24941)
-- Name: price_update price_update_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.price_update
    ADD CONSTRAINT price_update_pkey PRIMARY KEY (id);


--
-- TOC entry 1923 (class 2606 OID 24883)
-- Name: product product_name_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_name_unique UNIQUE (name);


--
-- TOC entry 1925 (class 2606 OID 24885)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 1927 (class 2606 OID 24887)
-- Name: purchase purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);


--
-- TOC entry 1915 (class 2606 OID 24888)
-- Name: product quantity_check; Type: CHECK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE public.product
    ADD CONSTRAINT quantity_check CHECK ((stock >= 0)) NOT VALID;


--
-- TOC entry 1929 (class 2606 OID 24890)
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- TOC entry 1934 (class 2606 OID 24891)
-- Name: purchase accountid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT accountid_fk FOREIGN KEY (accountid) REFERENCES public.account(id);


--
-- TOC entry 1937 (class 2606 OID 24942)
-- Name: price_update accountid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.price_update
    ADD CONSTRAINT accountid_fk FOREIGN KEY (accountid) REFERENCES public.account(id);


--
-- TOC entry 1932 (class 2606 OID 24896)
-- Name: like like_product; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."like"
    ADD CONSTRAINT like_product FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- TOC entry 1933 (class 2606 OID 24901)
-- Name: like like_user; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."like"
    ADD CONSTRAINT like_user FOREIGN KEY (userid) REFERENCES public.account(id);


--
-- TOC entry 1935 (class 2606 OID 24906)
-- Name: purchase productid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT productid_fk FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- TOC entry 1938 (class 2606 OID 24947)
-- Name: price_update productid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.price_update
    ADD CONSTRAINT productid_fk FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- TOC entry 1936 (class 2606 OID 24911)
-- Name: token userid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT userid_fk FOREIGN KEY (userid) REFERENCES public.account(id);


-- Completed on 2018-12-26 19:33:51 UTC

--
-- PostgreSQL database dump complete
--