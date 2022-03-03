DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

create table koordinaten
(
    id integer generated always as identity primary key ,
    breite_grad integer ,
    breite_richtung varchar (1),
    laenge_grad integer ,
    laenge_richtung varchar (1)
);

create table wetter
(
    koord_id integer ,
    zeit date ,
    feuchtigkeit integer ,
    wind_geschw integer,
    primary key (koord_id, zeit),
    foreign key (koord_id) references koordinaten on delete cascade
);

create table insel
(
    id integer generated always as identity primary key ,
    koord_id integer ,
    name varchar (30),
    abstand integer ,
    foreign key (koord_id) references koordinaten on delete cascade
);

create table bewohner
(
    id integer generated always as identity primary key ,
    insel_id integer ,
    name varchar (30),
    alter integer ,
    hobby varchar (30),
    beruf varchar (30),
    beruf_erfahrung integer ,
    status varchar (30),
    IQ integer ,
    einkommen integer ,
    foreign key (insel_id) references insel on delete set null
);

create table produkt
(
    id integer generated always as identity primary key ,
    besitzer_id integer ,
    name varchar (30),
    preis integer ,
    menge integer ,
    status varchar (30),
    kategorie varchar (30),
    foreign key (besitzer_id) references bewohner on delete set null
);

-- datenbank für 3.Level

create table hersteller
(
    id integer generated always as identity primary key ,
    name varchar (30),
    bekanntheitsgrad integer
);

create table ware
(
    id integer generated always as identity primary key ,
    hersteller_id integer ,
    name varchar (30),
    preis integer ,
    foreign key (hersteller_id) references hersteller on delete cascade
);

create table kaeufe
(
    id integer generated always as identity primary key,
    ware_id integer ,
    anzahl integer ,
    tag date ,
    foreign key (ware_id) references ware on delete cascade
);


-- game logic

create table spieler
(
    id integer generated always as identity primary key ,
    name varchar (30),
    passwort varchar (100)
);

create table frage
(
    id integer generated always as identity primary key ,
    text varchar (2000) ,
    punkte integer ,
    antw varchar (2000),
    tips varchar (2000)
);

create table spielstand
(
    spieler_id integer ,
    akt_frage_id integer,
    level integer ,
    punkte integer ,
    zeit integer ,
    primary key (spieler_id),
    foreign key (akt_frage_id) references frage on delete set null,
    foreign key (spieler_id) references spieler on delete cascade
);

create table ubersprungenFragen
(
    spieler_id integer ,
    frage_id integer ,
    foreign key (spieler_id) references spieler on delete cascade
);