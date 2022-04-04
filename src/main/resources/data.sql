-- koordinaten

insert into koordinaten (breite_grad,breite_richtung,laenge_grad,laenge_richtung)
values (40, 'S', 50, 'W');
insert into koordinaten (breite_grad,breite_richtung,laenge_grad,laenge_richtung)
values (50, 'N', 90, 'W');
insert into koordinaten (breite_grad,breite_richtung,laenge_grad,laenge_richtung)
values (20, 'N', 140, 'E');
insert into koordinaten (breite_grad,breite_richtung,laenge_grad,laenge_richtung)
values (40, 'N', 45, 'E');
insert into koordinaten (breite_grad,breite_richtung,laenge_grad,laenge_richtung)
values (80, 'N', 80, 'W');
insert into koordinaten (breite_grad,breite_richtung,laenge_grad,laenge_richtung)
values (20, 'N', 80, 'W');

-- insel
insert into insel (koord_id, name, abstand)
values (1, 'Tucu' , 0);
insert into insel (koord_id, name, abstand)
values (2, 'Ohara', 100000);
insert into insel (koord_id, name, abstand)
values (3, 'Egana', 180000);
insert into insel (koord_id, name, abstand)
values (4, 'Gecko', 130000);
insert into insel (koord_id, name, abstand)
values (5, 'Konomi', 135000);

-- wetter
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (1, '2021-10-20', 80, 20);
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (2, '2021-10-20', 75, 30);
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (3, '2021-10-20', 60, 50);
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (4, '2021-10-20', 90, 200);
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (5, '2021-10-20', 65, 10);
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (6, '2021-11-19', 85, 40);
insert  into wetter (koord_id,zeit,feuchtigkeit,wind_geschw)
values (6, '2021-11-21', 100, 60);



-- bewohner
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Jadon', 21, 'Fußball', 'Guitarist', 5, 'frei', 90, 3000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Alex', 24, 'Abenteuer', 'Navigator', 5, 'beschaeftigt', 85, 4000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Malen', 35, 'Reisen', 'Koch', 10, 'frei', 90, 3000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Tuco', 44, 'Reisen', 'Koch', 10, 'beschaeftigt', 90, 3000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Jadon', 21, 'Volleyball', 'Koch', 8, 'beschaeftigt', 90, 2500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Thomas', 21, 'Reisen', 'Saenger', 5, 'frei', 100,3500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Ana', 29, 'Reisen', 'Navigator', 4, 'frei', 110,5000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Jadon', 41, 'Lesen', 'Pianist', 20, 'frei', 89,2000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Lena', 31, 'Tennis', 'Koch', 5, 'frei', 90, 1500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Petra', 56, 'Tennis', 'Kauffrau', 5, 'beschaeftigt', 98, 4200);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Tung', 27, 'Reisen', 'Student', 0, 'frei', 99, 300);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Nami', 21, 'Abenteuer', 'Navigator', 3, 'frei', 92, 9000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Bruno', 53, 'Sport', 'Baecker', 30, 'frei', 89, 2400);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Markus', 59, 'Sport', 'Schiffbauer', 30, 'beschaeftigt', 89,6000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Philipp', 44, 'Arbeiten', 'Schiffbauer', 10, 'frei', 98, 2500);
----
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Franziska', 20, 'Lesen', 'Musiker', 2, 'frei', 98, 6500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Ida', 39, 'Musik', 'Musiker', 10, 'beschaeftigt', 92, 8200);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Johanna', 28, 'Sport', 'Musiker', 6, 'frei', 98, 9000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'David', 31, 'Archaeologie', 'Techniker', 10, 'frei', 98, 3500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Luca', 46, 'Archaeologie', 'Baecker', 20, 'beschaeftigt', 90, 1800);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Mina', 29, 'Lesen', 'Koch', 9, 'beschaeftigt', 92, 1500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Isa', 28, 'Archaeologie', 'Navigator', 11, 'beschaeftigt', 110, 8100);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Nono', 28, 'Eishockey', 'Navigator', 11, 'beschaeftigt', 115, 6000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Edga', 28, 'Archaeologie', 'Navigator', 11, 'beschaeftigt', 111, 8000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Kim', 44, 'Tennis', 'Techniker', 20, 'frei', 100, 4500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Thomas', 44, 'Reisen', 'Arzt', 10, 'beschaeftigt', 110, 6900);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Kai', 32, 'Reisen', 'Arzt', 6, 'beschaeftigt', 109, 8400);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Markus', 36, 'Lesen', 'Arzt', 8, 'beschaeftigt', 120, 7200);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Morina', 34, 'Archaeologie', 'Archaeologe', 11, 'beschaeftigt', 101, 6033);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Felix', 59, 'Reisen', 'Arzt', 26, 'beschaeftigt', 120, 5900);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Tino', 43, 'Archaeologie', 'Arzt', 16, 'beschaeftigt', 111, 5000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (2, 'Morina', 64, 'Archaeologie', 'Archaeologe', 40, 'beschaeftigt', 111, 6030);




-- produkt
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (13, 'altes Schiff', 150,1, 'zu verkaufen', 'Wasserfahrzeug');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (14, 'altes Schiff', 100,2, 'privat', 'Wasserfahrzeug');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (1, 'Auto', 100,1, 'privat', 'Fahrzeug');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (13, 'Steuerrad', 20,2, 'zu verkaufen', 'Ersatzteil');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (2, 'Reis', 5,2, 'privat', 'Lebensmittel');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (6, 'Segel', 20,1, 'privat', 'Ersatzteil');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (6, 'Segel', 20,1, 'zu verkaufen', 'Ersatzteil');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (4, 'Rindfleisch', 15,2, 'zu verkaufen', 'Lebensmittel');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (5, 'Rindfleisch', 15,1, 'privat', 'Lebensmittel');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (10, 'Schweinefleisch', 11,40, 'privat', 'Lebensmittel');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (11, 'Hasenfleisch', 14,20, 'zu verkaufen', 'Lebensmittel');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (9, 'Kartoffel', 4,50, 'zu verkaufen', 'Lebensmittel');
insert into produkt (besitzer_id,name,preis,menge,status,kategorie)
values (11, 'Mehl', 2,25, 'zu verkaufen', 'Lebensmittel');


-- hersteller
insert into hersteller (name, bekanntheitsgrad)
values ('Kyoto', 1);
insert into hersteller (name, bekanntheitsgrad)
values ('Hanako', 1);
insert into hersteller (name, bekanntheitsgrad)
values ('Akane', 2);
insert into hersteller (name, bekanntheitsgrad)
values ('Hisa', 2);
insert into hersteller (name, bekanntheitsgrad)
values ('Chiyo', 3);
insert into hersteller (name, bekanntheitsgrad)
values ('Daichi', 4);
insert into hersteller (name, bekanntheitsgrad)
values ('Genki', 5);
insert into hersteller (name, bekanntheitsgrad)
values ('Jiro', 5);
insert into hersteller (name, bekanntheitsgrad)
values ('Kaori', 5);

-- ware
insert into ware (hersteller_id, name,preis)
values (8, 'Uhr', 20000); --1
insert into ware (hersteller_id, name,preis)
values (8, 'Geldbeutel', 9000); --2
insert into ware (hersteller_id, name,preis)
values (9, 'Schuhe', 4000); --3
insert into ware (hersteller_id, name,preis)
values (9, 'Jeans', 5000); --4
insert into ware (hersteller_id, name,preis)
values (9, 'Jacke', 40000); --5
insert into ware (hersteller_id, name,preis)
values (9, 'T-Shirt', 3000); --6
insert into ware (hersteller_id, name,preis)
values (6, 'Auto Daichi S1', 4000000); --7
insert into ware (hersteller_id, name,preis)
values (6, 'Motorrad Daichi M1', 500000); --8
insert into ware (hersteller_id, name,preis)
values (6, 'Auto Daichi limited', 10000000); --9
insert into ware (hersteller_id, name,preis)
values (5, 'Ring', 8100); --10
insert into ware (hersteller_id, name,preis)
values (5, 'Handschuhe', 1600); --11
insert into ware (hersteller_id, name,preis)
values (5, 'Pulli', 2100); --12



-- käufe
insert into kaeufe (ware_id, anzahl, tag)
values (1, 1, '2021-01-01');
insert into kaeufe (ware_id, anzahl, tag)
values (2, 2, '2021-01-02');
insert into kaeufe (ware_id, anzahl, tag)
values (1, 3, '2021-01-03');
insert into kaeufe (ware_id, anzahl, tag)
values (8, 1, '2021-01-03');
insert into kaeufe (ware_id, anzahl, tag)
values (7, 1, '2021-01-03');
insert into kaeufe (ware_id, anzahl, tag)
values (9, 1, '2021-01-03');
insert into kaeufe (ware_id, anzahl, tag)
values (10, 5, '2021-01-08');
insert into kaeufe (ware_id, anzahl, tag)
values (11, 3, '2021-01-08');
insert into kaeufe (ware_id, anzahl, tag)
values (12, 8, '2021-01-08');
insert into kaeufe (ware_id, anzahl, tag)
values (3, 2, '2021-01-11');
insert into kaeufe (ware_id, anzahl, tag)
values (9, 1, '2021-01-18');
insert into kaeufe (ware_id, anzahl, tag)
values (2, 1, '2021-01-21');
insert into kaeufe (ware_id, anzahl, tag)
values (4, 5, '2021-01-28');
insert into kaeufe (ware_id, anzahl, tag)
values (5, 3, '2021-01-28');
insert into kaeufe (ware_id, anzahl, tag)
values (7, 1, '2021-01-31');
insert into kaeufe (ware_id, anzahl, tag)
values (8, 1, '2021-01-31');
insert into kaeufe (ware_id, anzahl, tag)
values (9, 2, '2021-02-01');
insert into kaeufe (ware_id, anzahl, tag)
values (6, 3, '2021-02-02');


-- frage
--1
insert into frage (text, punkte, antw, tips) values ('Du liebst Abenteuer und möchtest endlich reisen.
Dafür brauchst du eine  Mannschaft und mentale Stärke, um die Herausforderungen zu bezwingen.
Je schneller du die Probleme löst, desto besser ist dein Ranking.
Viel Spaß !!!', 0, null ,null );
--2
insert into frage (text, punkte, antw,tips )
values ('Zeig bitte die Tabelle bewohner an [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1)' ||
                                                  'select * from bewohner','select * from <tabelle_name>');
--3
insert into frage (text, punkte, antw, tips )
values ('Du sollst erstmal einen Koch suchen. Finde alle Bewohner, die als Koch arbeiten.
         Hinweis : Achte auf die Großbuchstaben [10p]',
        10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select * from bewohner where beruf = \"Koch\"'
        ,'Benutze die Where - Klause, um die Bedingungen für Suchen zu stellen');
--4
insert into frage (text, punkte, antw, tips )
values ('Suche die Köche, die frei sind und sein Hobby Reisen ist [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select * from bewohner where beruf = \"Koch\" and hobby = \"Reisen\" and status = \"frei\"',
        'in der Where-Klause die Bedingungen mit logischen Operatoren wie *and* oder *or* zu verknüpfen');
--5
insert into frage (text, punkte, antw, tips)
values ('Suche alle Bewohner, die als Navigator arbeiten [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select * from bewohner where beruf = \"Navigator\"'
        ,'Achten Sie auf Großbuchstaben');
--6
insert into frage (text, punkte, antw, tips)
values ('Suche die Id und Name des Navigators, der mit mindestens 5 Jahren Erfahrung hat [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select id, name from bewohner where beruf = \"Navigator\" and beruf_erfahrung >= 5',
        'Achte darauf, dass nur id und name des Navigators erforderlich ist. SQL bietet Vergleichsoperatoren wie >, <, >=, <=');
--7
insert into frage (text, punkte, antw, tips)
values ('Alex ist leider beschäftigt. ' ||
        'Suche den jüngsten Navigator, der mindestens 3 Jahre Erfahrung hat und noch frei ist [30p]', 30,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select * from bewohner where beruf = \"Navigator\" and alter = (select min(alter) from bewohner ' ||
        'where beruf = \"Navigator\" and status = \"frei\" and beruf_erfahrung >= 3)',
        'Hier brauchst du eine Unteranfrage, um das kleinste Alter der Navigatoren erstmal zu ermitteln.' ||
        'Du kannst aber vorerst diese Frage überspringen.
         *with* - Klause ist hier nicht möglich');
--8
insert into frage (text, punkte, antw, tips)
values ('Jetzt brauchst du noch einen Musiker', 0, null ,'');
--9
insert into frage (text, punkte, antw, tips)
values ('Suche die Bewohner, die als Saenger , Gitarrist oder Pianist tätig sind
         Außerdem ihr Hobby Reisen oder Abenteuer ist. [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select * from bewohner where (beruf = \"Saenger\" or beruf = \"Gitarrist\" or beruf = \"Pianist\")' ||
        'and (hobby = \"Reisen\" or hobby = \"Abenteuer\")',
        'achten Sie auf die logische Operatoren (and, or) und den Klammern');
--10
insert into frage (text, punkte, antw, tips)
values ('Thomas ist bereit, mitzureisen', 0, null ,null);
--11
insert into frage (text, punkte, antw, tips)
values ('Suche alle Bewohner (nur ihre Id und Name erforderlich),' ||
        ' die als Schiffbauer arbeiten. [10p]'
        , 10, 'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
              'select id, name from bewohner where beruf = \"Schiffbauer\"','');
--12
insert into frage (text, punkte, antw, tips)
values ('Suche die Schiffbauer, die altes Schiff mit dem Status *zu verkaufen* haben.
        Hinweis : die Spalten für die Anfrage sind : bewohner.id, bewohner.name, produkt.name as produkt, produkt.preis, produkt.menge, produkt.status [10p]',
        10,'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
           'select bewohner.id, bewohner.name, produkt.name as produkt, produkt.preis, produkt.menge, produkt.status ' ||
           'from bewohner, produkt where bewohner.id = produkt.besitzer_id ' ||
           'and produkt.name =\"altes Schiff\" and produkt.status = \"zu verkaufen\"',
        'Join - Operator und Join-Bedingung.
         select-Teil der Anfrage sieht so aus: select bewohner.id, bewohner.name, produkt.name as produkt,produkt.preis,produkt.menge,produkt.status ');
insert into frage (text, punkte, antw, tips)
values ('Bruno ist bereit, sein altes Schiff zu verkaufen.
        Nach der Untersuchung brauchst du für das Schiff neuen Segel und Steuerrad.
        Suche die Bewohner, die entweder Segel oder Steuerrad zu verkaufen haben [10p]
        Hinweis: erforderliche Spalten : name (der Bewohner), produkt (Alias-Name für den Name des Produktes), preis (Preis des Produktes)', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select bewohner.name, produkt.name as produkt_name, produkt.preis from bewohner, produkt ' ||
        'where bewohner.id = produkt.besitzer_id ' ||
        'and (produkt.name = \"Segel\" or produkt.name = \"Steuerrad\") ' ||
        'and produkt.status = \"zu verkaufen\"','Join-Operator, logische Operatoren in where-Klause');
insert into frage (text, punkte, antw, tips)
values ('Jetzt brauchst du Lebensmittel für die Reise', 0, null,null);
insert into frage (text, punkte, antw, tips)
values ('Finden Sie heraus, was ist der durchschnittliche Preis aller Produkte aus der Tabelle produkt [10p]'
, 10, 'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
      'select avg(preis) from produkt'
,'Syntax für Durchschnitt: avg(<spalten_name>) ');
insert into frage (text, punkte, antw, tips)
values ('Was ist der durchschnittliche Preis aller Produkte jeder Kategorie.
        Der Preis soll aufsteigend sortiert werden.
        Hinweis : die Antwort hat 2 Spalten : kategorie, preis_durchschnitt.[20p]'
       , 20, 'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
             'select kategorie, avg(preis) as preis_durchschnitt from produkt group by kategorie order by preis_durchschnitt'
       ,'avg(preis), group by und order by ');
insert into frage(text, punkte, antw, tips)
values ('Got sei Dank ! Der Preis der Lebensmitteln ist am niedrigsten !', 0, null,'' );
insert into frage (text, punkte, antw, tips)
values ('Wenn du alle Lebensmitteln kaufen würdest, wie viel kostet es ?
        Hinweis : die Antwort hat eine Spalte : summe [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select sum(preis * menge) as summe from produkt'
        ,'sum(<rechnung>), man kann hier eine Rechnung durchführen');
insert into frage(text, punkte, antw, tips)
values ('Oh ! Das ist dann zu viel', 0, null,null );
insert into frage (text, punkte, antw, tips)
values ('Suche die Bewohner, die Fleisch zu verkaufen haben.
        Hinweis : Antwort hat 4 Spalten : name (Name des Bewohners), fleischsorte, preis, menge [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select bewohner.name, produkt.name as fleischsorte, produkt.preis, produkt.menge from bewohner, produkt ' ||
        'where bewohner.id = produkt.besitzer_id and produkt.name like \"%fleisch\" and produkt.status = \"zu verkaufen\"'
        ,'mit *like* kann man mehrere Fleischsorten abfragen' );
insert into frage (text, punkte, antw, tips)
values ('Jetzt brauchst du noch Reis, Mehl und Kartoffel.
        Suche alle Bewohner, die diese Produkte zu verkaufen haben.
        Hinweis: Antwort hat 4 Spalten : name (Name des Bewohner) , produkt, preis, menge [10p]', 10,
        'with bewohner as (select id, name, alter, hobby, beruf, beruf_erfahrung, status from bewohner where insel_id = 1) ' ||
        'select bewohner.name, produkt.name as produkt, preis, menge from bewohner, produkt' ||
        ' where bewohner.id = produkt.besitzer_id ' ||
        'and (produkt.name = \"Reis\" or produkt.name = \"Mehl\" or produkt.name = \"Kartoffel\")',
        'Join und logische Operatoren');
insert into frage (text, punkte, antw, tips)
values ('Dein Schiff ist aufgeladen, Sie können die endlich in den See stechen !!!',0,null ,null);
insert into frage (text, punkte, antw, tips)
values ('Die Navigation zeigt: die nächste Insel liegt im Norden. Finde alle Inseln im Norden.
        Hinweis : die Antwort hat nur 2 Spalten : id und name der Insel [10p]', 10,
        'select insel.id, insel.name from insel, koordinaten where breite_richtung = \"N\" and insel.koord_id = koordinaten.id '
        ,'Join zwischen insel und koordinaten Tabelle');
insert into frage (text, punkte, antw, tips)
values ('Nächte Information : die Insel liegt im NW , zeig bitte diese Inseln an.
        Hinweis : die Antwort hat nur 2 Spalten : id und name der Insel [10p]', 10,
        'select insel.id, insel.name from insel, koordinaten where breite_richtung = \"N\"' ||
        ' and laenge_richtung = \"W\" and insel.koord_id = koordinaten.id'
        ,'Join zwischen insel und koordinaten Tabelle');
insert into frage (text, punkte, antw, tips)
values ('Die nächste Insel ist eine dieser 2 Insel, welche die kleiner Abstand hat.
        Zeigen Sie die name und koordinaten dieser Insel an.
        Hinweis : die Spalten der Antwort sind : name, abstand, breite_grad, breite_richtung, laenge_grad, laenge_richtung [25p]', 25,
        'select name, abstand,breite_grad,breite_richtung, laenge_grad,laenge_richtung from insel, koordinaten ' ||
        'where insel.koord_id = koordinaten.id and (insel.id = 2 or insel.id = 5) ' ||
        'and insel.abstand = (select min(abstand) from insel where id = 2 or id = 5)'
         ,'merken Sie sich die id dieser Inseln von letzter Frage. Die sind 2 und 5');
insert into frage (text, punkte, antw, tips)
values ('Alles klar ! Die Insel Ohara ist unser nächstes Ziel !', 0, null ,null);
insert into frage (text, punkte, antw, tips)
values ('Auf dem Weg zum Ziel muss der Navigator immer auf das Wetter aufpassen.
        Wenn die Feuchtigkeit größer als 85% und Windgeschwindigkeit größer als 50km/h, ' ||
        'ist es höchst wahrscheinlich, dass es da bald ein Sturm kommt.' ||
        'Das Schiff ist in 2 Stunden ungefähr in Koordinaten 20°N 80°W. ' ||
        'Check bitte das Wetter dort (am 2021-11-21) [20p]', 20,
        'select breite_grad, breite_richtung , laenge_grad, laenge_richtung, feuchtigkeit, wind_geschw ' ||
        'from koordinaten, wetter ' ||
        'where koord_id = koordinaten.id and breite_grad = 20 and breite_richtung = \"N\" ' ||
        'and laenge_grad = 80 and laenge_richtung = \"W\" ' ||
        'and zeit = \"2021-11-21\"',
        'Join zwischen koordinaten und wetter und die zeit hat die Form : yyyy-mm-dd');
insert into frage (text, punkte, antw, tips)
values ('Oh, da kommt bald ein Sturm , wir müssen umfahren', 0, null ,null);
insert into frage (text, punkte, antw, tips)
values ('Deine Mannschaft ist auf die Insel Ohara gekommen hat aber kein Gold mehr um Lebensmitteln zu kaufen.
        Alle müssen arbeiten, um Gold zu verdienen. Du sollst für jeden ihrer Mannschaft einen Job suchen', 0, null,null );
insert into frage (text, punkte, antw, tips)
values ('Finden Sie das durchschnittliche Einkommen einzelner Berufsgruppe auf der Insel, geordnet nach dem Einkommen absteigend
        Hinweis : Antwort hat 2 Spalten : beruf und avg_einkommen [15p]', 15,
        'select beruf, avg(einkommen) as avg_einkommen from insel, bewohner ' ||
        'where insel.name = \"Ohara\" and bewohner.insel_id = insel.id ' ||
        'group by beruf order by avg_einkommen desc'
        ,'join von insel und bewohner, group by beruf und order by avg_einkommen desc ');
insert  into frage (text, punkte, antw, tips)
values ('Da verdient Musiker am meistens', 0, null,null);
insert  into frage (text, punkte, antw, tips)
values ('Suche den am bestens bezahlten Musiker, dann kann dein Musiker bei ihm arbeiten
        Hinweis : nur sein id, name und einkommen sind erforderlich [20p]', 20,
        'select bewohner.id, bewohner.name, einkommen from bewohner, insel where bewohner.insel_id = insel.id and insel.id = 2 and beruf =\"Musiker\"
        and einkommen = (select max (einkommen)  from bewohner where beruf = \"Musiker\")'
        ,'Unterfrage oder top zu benutzen');
insert into frage (text, punkte, antw, tips)
values ('Für den Koch suchst du jemanden, der Koch oder Baecker ist
        Hinweis : Die Antwort hat 3 Spalten: bewohner.id, bewohner.name, bewohner.beruf. [10p]', 10,
        'select bewohner.id, bewohner.name, bewohner.beruf from bewohner, insel ' ||
        'where insel.name = \"Ohara\" and bewohner.insel_id = insel.id ' ||
        'and (beruf = \"Koch\" or beruf = \"Baecker\")'
        ,'vergesst du join zwischen insel und bewohner nicht und du bist auf der Insel Ohara');
insert into frage (text, punkte, antw, tips)
values ('Der Koch kann bei dem Baecker Luca arbeiten',0,null ,null);
insert into frage (text, punkte, antw, tips)
values ('Jetzt bleibt noch der Navigator ',0,null ,null);
insert into frage (text, punkte, antw, tips)
values ('Finden Sie, wie viel Navigator auf der Insel Ohara es gibt ? [10p]',10,
        'select count (*) from insel, bewohner where insel.name = \"Ohara\" and insel.id = bewohner.insel_id and beruf = \"Navigator\"'
        ,'mit *count* kann man die Zeilen zählen');
insert into frage (text, punkte, antw, tips)
values ('Ein Navigator sucht nach einer Aushilfe. Sein Einkommen ist unter dem durchschnittlichen Einkommen aller Bewohner der Insel
        Hinweis : nur seine id und name erforderlich [20p]',20,
        'select bewohner.id, bewohner.name from insel, bewohner ' ||
        'where insel.name = \"Ohara\" and insel.id = bewohner.insel_id and bewohner.beruf = \"Navigator\" ' ||
        'and einkommen < (select avg(einkommen) from bewohner, insel where insel.name = \"Ohara\" and bewohner.insel_id = insel.id)'
        ,'du brauchst hier eine Unterfrage, um avg(einkommen) der Bewohner zu ermitteln.
         vergisst bitte nicht, dass du gerade auf der Insel Ohara bist');
insert into frage (text, punkte, antw, tips)
values ('Sehr Gut ! Alle haben jetzt einen Job', 0, null,null );
insert into frage (text, punkte, antw, tips)
values ('Auf dem See ist man leicht, krank zu werden. Du brauchst einen Arzt für seine Mannschaft', 0, null,null );
insert into frage (text, punkte, antw, tips)
values ('Ordne erstmal die Bewohner nach der Beruf-Gruppe das IQ - Ranking der Bewohner
        Hinweis : Du kannst  window, rank() function benutzen
        Die Spalten der Antwort sind : bewohner.id, bewohner.name, beruf , IQ, IQ_Ranking [30p]', 30,
        'select bewohner.id, bewohner.name, beruf, IQ, rank() over w as IQ_Ranking from bewohner, insel where bewohner.insel_id = insel.id' ||
        ' and insel.name = \"Ohara\"
         window w as (partition by beruf order by IQ desc)'
         ,'window-funktion ist hier der Schlüssel');
insert into frage (text, punkte, antw, tips)
values ('Hier erkennt man, dass es keinen Arzt mit dem Platz 2 gibt.
        Da zwei Ärzte am 1.Platz sind. Löst du bitte das Problem [30p]',
        30, 'select bewohner.id, bewohner.name, beruf, IQ, dense_rank() over w as IQ_Ranking from bewohner, insel
            where bewohner.insel_id = insel.id
            and insel.name = \"Ohara\"
            window w as (partition by beruf order by IQ desc)',
            'In der letzte Frage hast du die Bewohner nach dem beruf IQ - Ranking der Bewohner jeder Gruppe geordnet
            .In diesem Fall bietet SQL *dense_rank()* an' );
insert into frage (text, punkte, antw, tips)
values ('Der Arzt, wer bereit ist, mitzukommen, hat IQ Ranking 3 in seiner Gruppe.
         Empfehlung : benutzen Sie window und dense_rank() function' ||
        'Hinweis : Antwort hat die Spalten : id, name, iq und beruf [30p]', 30,
        'select	 id, name, iq, beruf
         from	 (select bewohner.id, bewohner.name, beruf, IQ, dense_rank() over w as IQ_Ranking from bewohner, insel where bewohner.insel_id = insel.id
	             and insel.name = \"Ohara\"
	             window w as (partition by beruf order by IQ desc)) as iq_tabelle_ranking
         where   iq_ranking = 3 and beruf = \"Arzt\"'
         ,'Unterabfrage mit window-Funktion in From-Klause');
insert into frage (text, punkte, antw, tips)
values ('Sehr gut ! Ihre Mannschaft hat jetzt genug Gold, um das Schiff wieder bereit zu machen
        Jetzt müssen Sie die nächste Insel finden', 0, null,null );
insert into frage (text, punkte, antw, tips)
values ('Die nächste Insel liegt auch im NW und hat Breitengrad und Längengrad gleich. Finde sie bitte heraus.
        Hinweis : Spalten der Antwort : insel.name, breite_grad, breite_richtung, laenge_grad, laenge_richtung [10p]'
        ,10, 'select insel.name, breite_grad, breite_richtung, laenge_grad, laenge_richtung ' ||
             'from insel, koordinaten ' ||
             'where koordinaten.id = insel.koord_id and breite_richtung = \"N\" and laenge_richtung = \"W\"' ||
             'and breite_grad = laenge_grad'
             ,'join zwischen insel und koordinaten');
insert into frage (text, punkte, antw, tips)
values ('Ok, die nächste Insel ist Konomi !', 0, null,null );
insert into frage (text, punkte, antw, tips)
values ('Auf der Insel Konomi herrscht ein Diktator. Die  Revolteure haben deine Mannschaft gebeten, sie anzuschließen, um diesen Diktator zu stürzen. ' ||
        'Obwohl das Volk in Armut lebt , hat der Diktator ein Luxusleben. Du sollst sein Luxusleben enthüllen.
        Hinweis: Die folgenden Fragen beziehen sich auf die Tabellen: ware, kaeufe und hersteller', 0, null ,null);
insert into frage (text, punkte, antw, tips)
values ('Wie viele Hersteller des Bekanntheitsgrades 5 gibt es ? [10p]', 10, 'select count(*) from hersteller where bekanntheitsgrad = 5'
,'count und where-bedingung beachten');
insert into frage (text , punkte, antw, tips)
values ('Wie oft hat der Diktator im 01.2021 Produkte von einem Hersteller des Bekanntheitsgrades größer als oder gleich 4 gekauft?
        Hinweis : Antwort hat eine Spalte bestellung_anzahl. [30p]', 30,
        'select count (*) as bestellung_anzahl from kaeufe, ware, hersteller where kaeufe.ware_id = ware.id and ware.hersteller_id = hersteller.id
                and tag <= \"2021-01-31\" and tag >= \"2021-01-01\" and hersteller.bekanntheitsgrad >= 4'
                ,'<, >, >=,<= sind auch mit dem Date-Datentyp einsetzbar
                 Join zwischen ware, kaeufe und hersteller');
/*insert into frage (text, punkte, antw, tips)
values ('Wie oft hat er im 01.2021 Produkte von einem Hersteller des Bekanntheitsgrades größer als 3 gekauft? [10p]', 10,
        'select count (*) ' ||
        'from kaeufe k, ware w, hersteller h
        where k.ware_id = w.id and w.hersteller_id = h.id
        and tag >= \"2021-01-01\"  and tag <= \"2021-01-31\" and bekanntheitsgrad > 3',null);*/
insert into frage (text, punkte, antw, tips)
values ('Wie ist der Kaufbetrag im 01.2021 ? [10p]' , 10,
        'select sum(anzahl * preis) as Kaufbetrag from kaeufe, ware ' ||
        'where kaeufe.ware_id = ware.id and tag >= \"2021-01-01\"  and tag <= \"2021-01-31\"'
        ,'join zwischen kaeufe und ware');
insert into frage (text, punkte, antw, tips)
values ('Was ist der höchste Betrag, den er am einem Tag gekauft hat ?
        Hinweis : Die Antwort hat die einzige Spalte max_betrag [20p]', 20,
        'select max (tag_kaufbetrag) as max_betrag from
        (select sum(anzahl * preis) as tag_kaufbetrag from kaeufe, ware where kaeufe.ware_id = ware.id group by tag) as tmp',
        'eine unterfrage mit *group by tag*, um Kaufbeträge aller Tage zu ermittelt und dann das Maximal');
insert into frage (text, punkte, antw, tips)
values ('Was ist der durchschnittliche Kaufbetrag jeden Tag vom 01.01.2021 bis 31.01.2021 ?
        Hinweis : die Antwort hat eine Spalte kaufbetrag_durchschnitt [20p]', 20,
        'select avg (kaufbetrag_pro_tag) from
	        (select sum(preis*anzahl) as kaufbetrag_pro_tag
	        from kaeufe, ware where kaeufe.ware_id = ware.id
	        and tag <= \"2021-01-31\" and tag >= ''2021-01-01\" group by tag) as tmp',
        'eine Unterfrage, um die Kaufbeträge aller Tage in diesem Zeitraum zu berechnen und dann den Durchschnitt');
insert into frage (text, punkte, antw, tips)
values ('Am welchen Tag hat der mehr als Durchschnitt gekauft ?
        Hinweis: die Antwort hat 2 Spalten : tag, kaufbetrag_pro_tag [25p]', 25,
        'with kaufbetrag as (select tag, sum(preis*anzahl) as kaufbetrag_pro_tag from kaeufe, ware where kaeufe.ware_id = ware.id group by tag)
            select *  from kaufbetrag where kaufbetrag_pro_tag > (select avg(kaufbetrag_pro_tag) from kaufbetrag)',
        'mit *with* kann man lokal eine Tabelle aufbauen. Der Code ist dann übersichtlicher');
insert into frage (text, punkte, antw, tips)
values ('Mit deiner Hilfe wurde der Diktator gestürzt.', 0, null ,null);


