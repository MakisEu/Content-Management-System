--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: article; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.article (
    body text,
    content_id character varying(100) NOT NULL,
    author character varying(75)
);


--
-- Name: banneddb; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.banneddb (
    user_id character varying(100) NOT NULL
);


--
-- Name: comment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.comment (
    text character varying(250),
    owner character varying(75),
    unique_id character varying(85) NOT NULL,
    charlimit smallint,
    votes integer,
    parent_content character varying(100),
    parent_comment character varying(85)
);


--
-- Name: content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.content (
    title character varying(100),
    charlimit smallint,
    votes integer,
    content_id character varying(100) NOT NULL,
    owner character varying(75)
);


--
-- Name: contentdb; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.contentdb (
    type character varying(20),
    content_owner character varying(75),
    content_id character varying(100) NOT NULL
);


--
-- Name: liked_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.liked_content (
    user_id character varying(75) NOT NULL,
    content_id character varying(100) NOT NULL,
    liked smallint
);


--
-- Name: post; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.post (
    body character varying(500),
    content_id character varying(100) NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    user_id character varying(100) NOT NULL,
    password character varying(70),
    type character varying(20)
);


--
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.article (body, content_id, author) FROM stdin;
\.


--
-- Data for Name: banneddb; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.banneddb (user_id) FROM stdin;
\.


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.comment (text, owner, unique_id, charlimit, votes, parent_content, parent_comment) FROM stdin;
\.


--
-- Data for Name: content; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.content (title, charlimit, votes, content_id, owner) FROM stdin;
\.


--
-- Data for Name: contentdb; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.contentdb (type, content_owner, content_id) FROM stdin;
\.


--
-- Data for Name: liked_content; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.liked_content (user_id, content_id, liked) FROM stdin;
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.post (body, content_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (user_id, password, type) FROM stdin;
\.


--
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (content_id);


--
-- Name: banneddb banneddb_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.banneddb
    ADD CONSTRAINT banneddb_pkey PRIMARY KEY (user_id);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (unique_id);


--
-- Name: content content_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.content
    ADD CONSTRAINT content_pkey PRIMARY KEY (content_id);


--
-- Name: contentdb contentdb_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contentdb
    ADD CONSTRAINT contentdb_pkey PRIMARY KEY (content_id);


--
-- Name: liked_content liked_content_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.liked_content
    ADD CONSTRAINT liked_content_pkey PRIMARY KEY (user_id, content_id);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (content_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: content content_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.content
    ADD CONSTRAINT content_id_fkey FOREIGN KEY (content_id) REFERENCES public.contentdb(content_id) ON DELETE CASCADE;


--
-- Name: post content_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT content_id_fkey FOREIGN KEY (content_id) REFERENCES public.content(content_id) ON DELETE CASCADE;


--
-- Name: article content_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT content_id_fkey FOREIGN KEY (content_id) REFERENCES public.content(content_id) ON DELETE CASCADE;


--
-- Name: liked_content liked_content_content_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.liked_content
    ADD CONSTRAINT liked_content_content_id_fkey FOREIGN KEY (content_id) REFERENCES public.content(content_id) ON DELETE CASCADE;


--
-- Name: liked_content liked_content_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.liked_content
    ADD CONSTRAINT liked_content_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: comment parent_comment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT parent_comment_fkey FOREIGN KEY (parent_comment) REFERENCES public.comment(unique_id) ON DELETE CASCADE;


--
-- Name: comment parent_content_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT parent_content_fkey FOREIGN KEY (parent_content) REFERENCES public.content(content_id) ON DELETE CASCADE;


--
-- Name: contentdb user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contentdb
    ADD CONSTRAINT user_fkey FOREIGN KEY (content_owner) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: banneddb user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.banneddb
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

