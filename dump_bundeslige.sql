--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: igraci; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.igraci (
    id integer NOT NULL,
    ime character varying(100) NOT NULL,
    prezime character varying(100) NOT NULL,
    pozicija character varying(100) NOT NULL,
    klub character varying(100) NOT NULL
);


ALTER TABLE public.igraci OWNER TO postgres;

--
-- Name: igraci_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.igraci_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.igraci_id_seq OWNER TO postgres;

--
-- Name: igraci_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.igraci_id_seq OWNED BY public.igraci.id;


--
-- Name: klub; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.klub (
    naziv character varying(100) NOT NULL,
    grad character varying(100) NOT NULL,
    godina_osnutka integer NOT NULL,
    stadion character varying(100) NOT NULL,
    trener integer NOT NULL,
    broj_trofeja_ligi integer NOT NULL,
    broj_igraca integer NOT NULL,
    prosjek_godina numeric(3,1) NOT NULL,
    broj_bodova_prosle_godine integer NOT NULL,
    broj_ligi_prvaka integer NOT NULL,
    broj_kupova integer NOT NULL,
    tm_vrijednost numeric(6,2) NOT NULL
);


ALTER TABLE public.klub OWNER TO postgres;

--
-- Name: stadion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stadion (
    naziv character varying(100) NOT NULL,
    kapacitet integer NOT NULL,
    godina_osnutka integer NOT NULL
);


ALTER TABLE public.stadion OWNER TO postgres;

--
-- Name: trener; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trener (
    id integer NOT NULL,
    ime character varying(100) NOT NULL,
    prezime character varying(100) NOT NULL
);


ALTER TABLE public.trener OWNER TO postgres;

--
-- Name: trener_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.trener_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.trener_id_seq OWNER TO postgres;

--
-- Name: trener_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.trener_id_seq OWNED BY public.trener.id;


--
-- Name: igraci id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.igraci ALTER COLUMN id SET DEFAULT nextval('public.igraci_id_seq'::regclass);


--
-- Name: trener id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trener ALTER COLUMN id SET DEFAULT nextval('public.trener_id_seq'::regclass);


--
-- Data for Name: igraci; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.igraci (id, ime, prezime, pozicija, klub) FROM stdin;
1	Thomas	Müller	Napadač	Bayern Munich
2	Manuel	Neuer	Golman	Bayern Munich
3	Harry	Kane	Napadač	Bayern Munich
4	Alphonso	Davies	Obrana	Bayern Munich
5	Niklas	Sule	Obrana	Borussia Dortmund
6	Yan	Couto	Obrana	Borussia Dortmund
7	Gregor	Kobel	Golman	Borussia Dortmund
8	Emre	Can	Vezni	Borussia Dortmund
9	Xavi	Simons	Vezni	RB Leipzig
10	David	Raum	Obrana	RB Leipzig
11	Lukas	Klostermann	Obrana	RB Leipzig
12	Yussuf	Poulsen	Napadač	RB Leipzig
13	Deniz	Undav	Napadač	Stuttgart
14	Enzo	Millot	Vezni	Stuttgart
15	Jeff	Chabot	Obrana	Stuttgart
16	Alexander	Nübel	Golman	Stuttgart
17	Jonas	Wind	Napadač	Wolfsburg
18	Maximilian	Arnold	Vezni	Wolfsburg
19	Sebastiaan	Bornauw	Obrana	Wolfsburg
20	Kamil	Grabara	Golman	Wolfsburg
21	Florian	Wirtz	Vezni	Bayer Leverkusen
22	Victor	Boniface	Napadač	Bayer Leverkusen
23	Jeremie	Frimpong	Obrana	Bayer Leverkusen
24	Jonathan	Tah	Obrana	Bayer Leverkusen
25	Omar	Marmoush	Napadač	Eintracht Frankfurt
26	Mario	Götze	Vezni	Eintracht Frankfurt
27	Ansgar	Knauff	Obrana	Eintracht Frankfurt
28	Robin	Koch	Obrana	Eintracht Frankfurt
29	Julian	Weigl	Vezni	Borussia Mönchengladbach
30	Joe	Scally	Obrana	Borussia Mönchengladbach
31	Luca	Netz	Obrana	Borussia Mönchengladbach
32	Ko	Itakura	Obrana	Borussia Mönchengladbach
33	Andrej	Kramarić	Napadač	TSG Hoffenheim
34	Oliver	Baumann	Golman	TSG Hoffenheim
35	Ozan	Kabak	Obrana	TSG Hoffenheim
36	Dennis	Geiger	Vezni	TSG Hoffenheim
37	Finn	Dahmen	Golman	FC Augsburg
38	Maximilian	Bauer	Obrana	FC Augsburg
39	Robert	Gumny	Obrana	FC Augsburg
40	Jeffrey	Gouweleeuw	Obrana	FC Augsburg
41	Diogo	Leite	Obrana	Union Berlin
42	Lucas	Tousart	Vezni	Union Berlin
43	Frederik	Rønnow	Golman	Union Berlin
44	Janik	Haberer	Vezni	Union Berlin
45	Matthias	Ginter	Obrana	SC Freiburg
46	Maximilian	Eggestein	Vezni	SC Freiburg
47	Ritsu	Doan	Napadač	SC Freiburg
48	Vincenzo	Grifo	Napadač	SC Freiburg
49	Robin	Zentner	Golman	1. FSV Mainz 05
50	Jae-Sung	Lee	Vezni	1. FSV Mainz 05
51	Jonathan	Burkardt	Napadač	1. FSV Mainz 05
52	Dominik	Kohr	Vezni	1. FSV Mainz 05
53	Michael	Zetterer	Golman	SV Werder Bremen
54	Miloš	Veljković	Obrana	SV Werder Bremen
55	Leonardo	Bittencourt	Vezni	SV Werder Bremen
56	Romano	Schmid	Vezni	SV Werder Bremen
\.


--
-- Data for Name: klub; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.klub (naziv, grad, godina_osnutka, stadion, trener, broj_trofeja_ligi, broj_igraca, prosjek_godina, broj_bodova_prosle_godine, broj_ligi_prvaka, broj_kupova, tm_vrijednost) FROM stdin;
Bayern Munich	Munich	1900	Allianz Arena	1	33	28	26.5	72	6	20	939.70
Borussia Dortmund	Dortmund	1909	Signal Iduna Park	2	8	25	25.1	65	1	5	475.30
RB Leipzig	Leipzig	2009	Red Bull Arena	3	0	24	25.6	63	0	2	517.90
Stuttgart	Stuttgart	1893	Mercedes-Benz Arena	4	5	31	24.2	73	0	3	313.73
Wolfsburg	Wolfsburg	1945	Volkswagen Arena	5	1	31	25.4	37	0	1	215.00
Bayer Leverkusen	Leverkusen	1904	BayArena	6	1	25	26.0	90	0	2	634.25
Eintracht Frankfurt	Frankfurt	1899	Deutsche Bank Park	7	1	27	25.0	47	0	5	280.10
Borussia Mönchengladbach	Mönchengladbach	1900	Borussia-Park	8	5	26	26.2	34	0	3	139.70
TSG Hoffenheim	Sinsheim	1899	PreZero Arena	9	0	33	25.9	46	0	0	166.15
FC Augsburg	Augsburg	1907	WWK Arena	10	0	29	25.8	39	0	0	99.33
Union Berlin	Berlin	1966	Stadion An der Alten Försterei	11	0	29	27.0	33	0	0	122.18
SC Freiburg	Freiburg	1904	Europa-Park Stadion	12	0	28	26.7	42	0	0	165.05
1. FSV Mainz 05	Mainz	1905	Mewa Arena	13	0	25	26.8	35	0	0	93.45
SV Werder Bremen	Bremen	1899	Wohninvest Weserstadion	14	4	27	26.3	42	0	6	119.35
\.


--
-- Data for Name: stadion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stadion (naziv, kapacitet, godina_osnutka) FROM stdin;
Allianz Arena	75024	2005
Signal Iduna Park	81365	1974
Red Bull Arena	47800	2010
Mercedes-Benz Arena	60441	1933
Volkswagen Arena	30000	2002
BayArena	30210	1958
Deutsche Bank Park	51500	1925
Borussia-Park	54057	2004
PreZero Arena	30150	2009
WWK Arena	30660	2009
Stadion An der Alten Försterei	22012	1920
Europa-Park Stadion	34700	2021
Mewa Arena	34000	2011
Wohninvest Weserstadion	42100	1909
\.


--
-- Data for Name: trener; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trener (id, ime, prezime) FROM stdin;
1	Vincent	Kompany
2	Nuri	Sahin
3	Marco	Rose
4	Sebastian	Hoeneß
5	Ralph	Hasenhüttl
6	Xabi	Alonso
7	Dino	Toppmöller
8	Gerardo	Seoane
9	Pellegrino	Matarazzo
10	Jess	Thorup
11	Bo	Svensson
12	Julian	Schuster
13	Bo	Henriksen
14	Ole	Werner
\.


--
-- Name: igraci_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.igraci_id_seq', 56, true);


--
-- Name: trener_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trener_id_seq', 14, true);


--
-- Name: igraci igraci_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.igraci
    ADD CONSTRAINT igraci_pkey PRIMARY KEY (id);


--
-- Name: klub klub_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.klub
    ADD CONSTRAINT klub_pkey PRIMARY KEY (naziv);


--
-- Name: stadion stadion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stadion
    ADD CONSTRAINT stadion_pkey PRIMARY KEY (naziv);


--
-- Name: trener trener_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trener
    ADD CONSTRAINT trener_pkey PRIMARY KEY (id);


--
-- Name: igraci igraci_klub_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.igraci
    ADD CONSTRAINT igraci_klub_fkey FOREIGN KEY (klub) REFERENCES public.klub(naziv);


--
-- Name: klub klub_stadion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.klub
    ADD CONSTRAINT klub_stadion_fkey FOREIGN KEY (stadion) REFERENCES public.stadion(naziv);


--
-- Name: klub klub_trener_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.klub
    ADD CONSTRAINT klub_trener_fkey FOREIGN KEY (trener) REFERENCES public.trener(id);


--
-- PostgreSQL database dump complete
--

