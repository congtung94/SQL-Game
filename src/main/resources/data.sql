
-- insel daten
insert into insel (name, beschr) values ('frühling', 'sehr schön');
insert into insel (name, beschr) values ('sommer', 'sehr warm');
insert into insel (name, beschr) values ('winter', 'sehr kalt');

-- bewohner daten
insert into bewohner (name, ort_id, gold, beruf, status)
values ('lukas', 1, 200, 'arzt','frei');
insert into bewohner (name, ort_id, gold, beruf, status)
values ('tobias', 1, 0, 'händler','gefangen');
insert into bewohner (name, ort_id, gold, beruf, status)
values ('thomas', 1, 10, 'bauer','frei');
insert into bewohner (name, ort_id, gold, beruf, status)
values ('wayne', 2, 3000, 'käufer','frei');
insert into bewohner (name, ort_id, gold, beruf, status)
values ('jadon', 2, 2000, 'techniker','frei');

-- kategorie daten
insert into kategorie (name, beschr) values ('lebensmittel', 'verdorben');
insert into kategorie (name, beschr) values ('trinken', 'zum trinken');
insert into kategorie (name, beschr) values ('haushalt', 'guter Zustand');
insert into kategorie (name, beschr) values ('schuhe', 'adidas');
insert into kategorie (name, beschr) values ('sport', 'baseball sache');
insert into kategorie (name, beschr) values ('accessoires', 'kleine teile');


-- produkt daten
insert into produkt (name, menge, besitzer_id, kat_id) values ('tasche', 4, 1, 6);
insert into produkt (name, menge, besitzer_id, kat_id) values ('ball', 1, 1, 5);
insert into produkt (name, menge, besitzer_id, kat_id) values ('wasser', 1, 2, 2);
insert into produkt (name, menge, besitzer_id, kat_id) values ('reis', 1, 2, 1);
insert into produkt (name, menge, besitzer_id, kat_id) values ('rindfleisch', 3, 2, 1);
insert into produkt (name, menge, besitzer_id, kat_id) values ('hühnerfleisch', 10, 5 , 1);
insert into produkt (name, menge, besitzer_id, kat_id) values ('kompass', 1, null , null);
insert into produkt (name, menge, besitzer_id, kat_id) values ('wasser', 1, null , 2);
insert into produkt (name, menge, besitzer_id, kat_id) values ('uhr', 1, 4, 6);
insert into produkt (name, menge, besitzer_id, kat_id) values ('cap', 3, 4, 6);
insert into produkt (name, menge, besitzer_id, kat_id) values ('feuerzeug', 2, 1, 3);

-- hobby daten
insert into hobby (name, beschr) values ('fußball', 'MANU fan');
insert into hobby (name, beschr)  values ('fußball', 'Arsenal fan');
insert into hobby (name, beschr)  values ('reisen', 'alleine');
insert into hobby (name, beschr)  values ('lesen', 'lesen lassen');
insert into hobby (name, beschr)  values ('essen', 'youtube fan');
insert into hobby (name, beschr)  values ('tisch tennis', 'kann nicht spielen');

-- bew_hobby daten
insert into bew_hobby (bew_id, hb_id) values (5,1);
insert into bew_hobby (bew_id, hb_id) values (5,2);
insert into bew_hobby (bew_id, hb_id) values (1,3);
insert into bew_hobby (bew_id, hb_id) values (2,1);
insert into bew_hobby (bew_id, hb_id) values (3,4);
insert into bew_hobby (bew_id, hb_id) values (3,5);
insert into bew_hobby (bew_id, hb_id) values (4,4);
insert into bew_hobby (bew_id, hb_id) values (5,6);
insert into bew_hobby (bew_id, hb_id) values (1,4);

-- lieferant daten
insert into lieferant (name ,beschr) values ('Delay' , 'immer verspätet, günstig');
insert into lieferant (name ,beschr) values ('Tiki' , 'schwere Pakete');
insert into lieferant (name ,beschr) values ('Blitz' , 'sofort lieferung, teuer');

-- bestellung daten
insert into bestellung (kaeufer_id, verkaeufer_id, bst_tag, lieferung_tag, lieferant_id)
values (1,4,'2021-03-20', '2021-03-20', 1);
insert into bestellung (kaeufer_id, verkaeufer_id, bst_tag, lieferung_tag, lieferant_id)
values (1,3,'2020-01-20', '2020-01-22', 2);
insert into bestellung (kaeufer_id, verkaeufer_id, bst_tag, lieferung_tag, lieferant_id)
values (3,4,'2020-05-01', '2020-05-02', 1);
insert into bestellung (kaeufer_id, verkaeufer_id, bst_tag, lieferung_tag, lieferant_id)
values (2,5,'2020-09-20', '2020-09-20', 2);
insert into bestellung (kaeufer_id, verkaeufer_id, bst_tag, lieferung_tag, lieferant_id)
values (5,4,'2021-10-11', '2021-10-20', 3);

-- bestellung_details daten
insert into bestellung_details (bst_id, prod_id, einzel_preis, bst_menge)
values (1, 2, 4.5, 4);
insert into bestellung_details (bst_id, prod_id, einzel_preis, bst_menge)
values (2, 4, 6.5, 40);
insert into bestellung_details (bst_id, prod_id, einzel_preis, bst_menge)
values (1, 6, 8.5, 11);
insert into bestellung_details (bst_id, prod_id, einzel_preis, bst_menge)
values (1, 8, 10.5, 2);
insert into bestellung_details (bst_id, prod_id, einzel_preis, bst_menge)
values (1, 5, 3.5, 9);


-- antworten
insert into antwort (col_anz, zeile_anz,typ, sql)
values (0,0,1, 'select count(*) from bewohner, insel where bewohner.ort_id = insel.id and insel.name = \"winter\"');
insert into antwort (col_anz, zeile_anz,typ, sql)
values (5,2,2, 'select * from produkt where produkt.besitzer_id is null');
insert into antwort (col_anz, zeile_anz, typ,sql)
values (0,0,1, 'select count(*) from bewohner, insel where bewohner.ort_id = insel.id and insel.name = \"frühling\"');
insert into antwort (col_anz, zeile_anz,typ, sql)
values (2,5,2, 'select  h.name as Hobby_name, count (bh.hb_id) as Interessiete
from hobby h, bew_hobby bh
where h.id = bh.hb_id
group by h.name
order by count(bh.hb_id) desc');
insert into antwort (col_anz, zeile_anz,typ, sql)
values (6,2,2, 'select * from bestellung where bst_tag = lieferung_tag');
insert into antwort (col_anz, zeile_anz,typ, sql)
values (3,3,2, 'select bew.name as kauefer_name,i.name as Insel_name , bst.id as bestellung_id
from bestellung bst, bewohner bew, insel i
where bst.kaeufer_id = bew.id and bew.ort_id = i.id and i.name = ''frühling'' and
(bst.verkaeufer_id  in (select bewohner.id from bewohner, insel where bewohner.ort_id = insel.id and insel.name = ''sommer''))');

-- fragen
insert into frage (text,level,max_punkte,antw_id)
values ('Die Insel heißt Sommer,kannst du checken, wie viele Menschen auf der Insel leben ?', 1, 10, 1);
insert into frage (text,level,max_punkte,antw_id)
values ('Auf der Insel kannst du Lebensmittel oder Gegenstände finden. Die findest du in der Tabelle Produkt, wo Besitzer null ist ', 1, 10, 2);
insert into frage (text,level,max_punkte,antw_id)
values ('Die Insel heißt Frühling,kannst du checken, wer auf der Insel lebt ?', 2, 15, 3);
insert into frage (text,level,max_punkte,antw_id)
values ('was sind die dominanten Hobby der Bewohner ? Die Ausgabe soll 2 Column Hobby_name und Interessierte enthalten', 2, 15, 4);
insert into frage (text,level,max_punkte,antw_id)
values ('welche Bestellungen werden am gleichen Tag geliefert ?', 3, 20, 5);
insert into frage (text,level,max_punkte,antw_id)
values ('welche Bestellungen sind zwischen 2 Insel,welche Insel Frühling als Käufer ist ? ' ||
        'Die Ausgabe sollen 3 Spalten: Kaeufer_name, Insel_name und bestellung_id enthalten ', 3, 20, 6);


-- spieler
insert into spieler (name, passwort) values ('tung' , 'demo');
insert into spieler (name, passwort) values ('hai' , 'demo');
insert into spieler (name, passwort) values ('ba' , 'demo');

-- spielstand

insert into spielstand (spieler_id, level,punkte, zeit, akt_frage_id) values (1, 1, 0, 0, 1 );
insert into spielstand (spieler_id, level,punkte, zeit, akt_frage_id) values (2, 3, 3000, 2000, 5 );
insert into spielstand (spieler_id, level,punkte, zeit, akt_frage_id) values (3, 2, 200, 300, 3 );


