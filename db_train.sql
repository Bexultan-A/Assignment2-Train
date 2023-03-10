PGDMP          /                 {            db_train    15.1    15.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                        1262    16398    db_train    DATABASE        CREATE DATABASE db_train WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Kazakh_Kazakhstan.utf8';
    DROP DATABASE db_train;
                postgres    false            ?            1259    16450 	   passenger    TABLE     l   CREATE TABLE public.passenger (
    id bigint NOT NULL,
    personid bigint NOT NULL,
    wagonid bigint
);
    DROP TABLE public.passenger;
       public         heap    postgres    false            ?            1259    16449    passenger_id_seq    SEQUENCE     ?   ALTER TABLE public.passenger ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.passenger_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            ?            1259    16411    person    TABLE     ?   CREATE TABLE public.person (
    id integer NOT NULL,
    personname character varying(50) NOT NULL,
    age integer NOT NULL,
    personnumber character varying(20) NOT NULL,
    isdisabled boolean NOT NULL
);
    DROP TABLE public.person;
       public         heap    postgres    false            ?            1259    16418    person_id_seq    SEQUENCE     ?   ALTER TABLE public.person ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214            ?            1259    16438    train    TABLE     -  CREATE TABLE public.train (
    trainid bigint NOT NULL,
    fromadress character varying(20) NOT NULL,
    toadress character varying(20) NOT NULL,
    dateofdeparture character varying(20) NOT NULL,
    timeofdeparture character varying(20) NOT NULL,
    totaltime character varying(20) NOT NULL
);
    DROP TABLE public.train;
       public         heap    postgres    false            ?            1259    16437    train_trainid_seq    SEQUENCE     ?   ALTER TABLE public.train ALTER COLUMN trainid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.train_trainid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            ?            1259    16434    voucher    TABLE     z   CREATE TABLE public.voucher (
    voucherid bigint NOT NULL,
    personid bigint NOT NULL,
    isfree boolean NOT NULL
);
    DROP TABLE public.voucher;
       public         heap    postgres    false            ?            1259    16433    voucher_voucherid_seq    SEQUENCE     ?   ALTER TABLE public.voucher ALTER COLUMN voucherid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.voucher_voucherid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            ?            1259    16428    wagon    TABLE     ?   CREATE TABLE public.wagon (
    wagonid integer NOT NULL,
    typeofwagon character varying(50) NOT NULL,
    trainid integer NOT NULL,
    voucherid integer NOT NULL,
    numberofseats bigint
);
    DROP TABLE public.wagon;
       public         heap    postgres    false            ?            1259    16427    wagon_wagonid_seq    SEQUENCE     ?   ALTER TABLE public.wagon ALTER COLUMN wagonid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.wagon_wagonid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217                      0    16450 	   passenger 
   TABLE DATA           :   COPY public.passenger (id, personid, wagonid) FROM stdin;
    public          postgres    false    223   *                 0    16411    person 
   TABLE DATA           O   COPY public.person (id, personname, age, personnumber, isdisabled) FROM stdin;
    public          postgres    false    214   M                 0    16438    train 
   TABLE DATA           k   COPY public.train (trainid, fromadress, toadress, dateofdeparture, timeofdeparture, totaltime) FROM stdin;
    public          postgres    false    221   ?                 0    16434    voucher 
   TABLE DATA           >   COPY public.voucher (voucherid, personid, isfree) FROM stdin;
    public          postgres    false    219   f                  0    16428    wagon 
   TABLE DATA           X   COPY public.wagon (wagonid, typeofwagon, trainid, voucherid, numberofseats) FROM stdin;
    public          postgres    false    217   ?        !           0    0    passenger_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.passenger_id_seq', 1, true);
          public          postgres    false    222            "           0    0    person_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.person_id_seq', 18, true);
          public          postgres    false    215            #           0    0    train_trainid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.train_trainid_seq', 3, true);
          public          postgres    false    220            $           0    0    voucher_voucherid_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.voucher_voucherid_seq', 2, true);
          public          postgres    false    218            %           0    0    wagon_wagonid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.wagon_wagonid_seq', 1, true);
          public          postgres    false    216            ?           2606    16454    passenger passenger_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.passenger
    ADD CONSTRAINT passenger_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.passenger DROP CONSTRAINT passenger_pkey;
       public            postgres    false    223            z           2606    16415    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    214            ?           2606    16446    train train_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.train
    ADD CONSTRAINT train_pkey PRIMARY KEY (trainid);
 :   ALTER TABLE ONLY public.train DROP CONSTRAINT train_pkey;
       public            postgres    false    221            ~           2606    16444    voucher voucher_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.voucher
    ADD CONSTRAINT voucher_pkey PRIMARY KEY (voucherid);
 >   ALTER TABLE ONLY public.voucher DROP CONSTRAINT voucher_pkey;
       public            postgres    false    219            |           2606    16432    wagon wagon_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.wagon
    ADD CONSTRAINT wagon_pkey PRIMARY KEY (wagonid);
 :   ALTER TABLE ONLY public.wagon DROP CONSTRAINT wagon_pkey;
       public            postgres    false    217                  x?3???4?????? 	`         ?   x?-?;?0D??? ?c???p $*?$????(?훙??<?;?ETC?????cq??V<??????ig??ܟ/}?]?ׂE??j?Zu???>????-yC???????n$'j????%?s???2?W}??iED_O*/?         a   x?3?t,.I?K?H,??OI,?4?T?J?+M,??4??25?4?2?)r??M,??4U?M,J? ɛpq??%?gs"?0?21?42?????? ?j            x?3???,?????? 
??            x?3??)?H?4?4?44?????? (?l     