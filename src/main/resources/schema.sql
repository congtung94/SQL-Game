
create table insel
(
    id integer identity primary key ,
    name varchar (30) not null,
    beschr varchar (50)
);

create table bewohner
(
    id integer identity primary key ,
    name varchar (30) not null ,
    ort_id integer,
    gold integer,
    beruf varchar (30),
    status varchar (30),
    foreign key (ort_id) references insel on delete cascade
);

create table kategorie
(
    id integer identity primary key ,
    name varchar (30),
    beschr varchar (50)
);

create table produkt
(
    id integer identity primary key ,
    name varchar (30) not null ,
    menge integer ,
    besitzer_id integer ,
    kat_id integer ,
    foreign key (besitzer_id) references bewohner on delete set null ,
    foreign key (kat_id) references kategorie on delete set null
);


create table hobby
(
    id integer identity primary key ,
    name varchar (30),
    beschr varchar (50)
);

create table bew_hobby
(
    bew_id integer references bewohner on delete cascade ,
    hb_id integer references hobby on delete cascade,
    primary key (bew_id, hb_id)
);

-- game logic

create table antwort
(
    id integer identity primary key ,
    col_anz integer ,
    zeile_anz integer ,
    sql varchar (2000)
);

create table frage
(
    id integer identity primary key ,
    text varchar (2000) ,
    level integer ,
    max_punkte integer ,
    antw_id integer,
    foreign key (antw_id) references antwort on delete set null
);


create table lieferant
(
      id integer identity primary key ,
      name varchar (30),
      beschr varchar (50)
);

create table bestellung
(
    id integer identity primary key ,
    kaeufer_id integer ,
    verkaeufer_id integer ,
    bst_tag date ,
    lieferung_tag date ,
    lieferant_id integer ,
    foreign key (kaeufer_id) references bewohner on delete set null ,
    foreign key (verkaeufer_id) references bewohner on delete set null ,
    foreign key (lieferant_id) references bewohner on delete set null
);

create table bestellung_details
(
    bst_id integer references bestellung on delete cascade ,
    prod_id integer references produkt on delete cascade ,
    einzel_preis float ,
    bst_menge integer ,
    primary key (bst_id, prod_id)
);

create table spieler
(
    id integer identity primary key ,
    name varchar (30),
    passwort varchar (30)
);

create table spielstand
(
    spl_std_id integer identity primary key ,
    spieler_id integer ,
    level integer ,
    punkte integer ,
    zeit integer ,
    akt_frage_id integer,
    foreign key (akt_frage_id) references frage on delete set null,
    foreign key (spieler_id) references spieler on delete cascade
);