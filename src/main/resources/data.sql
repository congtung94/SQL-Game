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
values (1, 'jadon', 21, 'Fußball', 'Guitarist', 5, 'frei', 90, 3000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'alex', 24, 'Abenteuer', 'Navigator', 5, 'beschaeftigt', 85, 4000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'malen', 35, 'Reisen', 'Koch', 10, 'frei', 90, 3000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'Tuco', 44, 'Reisen', 'Koch', 10, 'beschaeftigt', 90, 3000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'jadon', 21, 'Volleyball', 'Koch', 8, 'beschaeftigt', 90, 2500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'thomas', 21, 'Reisen', 'Saenger', 5, 'frei', 100,3500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'ana', 29, 'Reisen', 'Navigator', 4, 'frei', 110,5000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'jadon', 41, 'Lesen', 'Pianist', 20, 'frei', 89,2000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'lena', 31, 'Reisen', 'Koch', 5, 'frei', 90, 1500);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'petra', 56, 'Tennis', 'Kauffrau', 5, 'beschaeftigt', 98, 4200);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'tung', 27, 'Reisen', 'Student', 0, 'frei', 99, 300);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'nami', 21, 'Abenteuer', 'Navigator', 3, 'frei', 92, 9000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'bruno', 53, 'Sport', 'Baecker', 30, 'frei', 89, 2400);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'markus', 59, 'Sport', 'Schiffbauer', 30, 'beschaeftigt', 89,6000);
insert into bewohner (insel_id,name,alter,hobby,beruf,beruf_erfahrung,status,IQ,einkommen)
values (1, 'philipp', 44, 'Arbeiten', 'Schiffbauer', 10, 'frei', 98, 2500);
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
insert into frage (text, punkte, antw) values ('Du liebst Abenteuer und möchtest endlich reisen.
Dafür brauchst du eine  Mannschaft und mentale Stärke, um die spannenden Aufgaben zu erledigen.
Je schneller du die Probleme löst, desto besser ist dein Ranking.
Viel Spaß !!!', 0, null );
--2
insert into frage (text, punkte, antw)
values ('Zeigen Sie die Tabelle Bewohner an', 10, 'select * from bewohner');
--3
insert into frage (text, punkte, antw)
values ('Suchen Sie erstmal einen Koch. Suchen Sie alle Bewohner, die als Koch arbeiten.' ||
        'Hinweis : Achten Sie auf Großbuchstaben',
        10, 'select * from bewohner where beruf = \"Koch\"');
--4
insert into frage (text, punkte, antw)
values ('Suchen Sie die Köche, die frei sind und sein Hobby Reisen ist', 10,
        'select * from bewohner where beruf = \"Koch\" and hobby = \"Reisen\" and status = \"frei\"');
--5
insert into frage (text, punkte, antw)
values ('Suchen Sie alle Bewohner, die als Navigator arbeiten', 10,
        'select * from bewohner where beruf = \"Navigator\"');
--6
insert into frage (text, punkte, antw)
values ('Suchen Sie die Id und Name des Navigators, der mit mindestens 5 Jahren Erfahrung hat', 10,
        'select id, name from bewohner where beruf = \"Navigator\" and beruf_erfahrung >= 5');
--7
insert into frage (text, punkte, antw)
values ('Alex ist leider beschäftigt. ' ||
        'Suchen Sie den jüngsten Navigator, der mindestens 3 Jahre Erfahrung hat und noch frei ist', 10,
        'select * from bewohner where beruf = \"Navigator\" and alter = (select min(alter) from bewohner ' ||
        'where beruf = \"Navigator\" and status = \"frei\" and beruf_erfahrung >= 3)');
--8
insert into frage (text, punkte, antw)
values ('Jetzt brauchen Sie noch einen Musiker', 0, null );
--9
insert into frage (text, punkte, antw)
values ('Suchen Sie die Bewohner, die als Sänger , Gitarrist oder Pianist frei sind ' ||
        'und das Hobby Reisen oder Abenteuer ist.', 10,
        'select * from bewohner where (beruf = \"Saenger\" or beruf = \"Gitarrist\" or beruf = \"Pianist\")' ||
        'and (hobby = \"Reisen\" or hobby = \"Abenteuer\")');
--10
insert into frage (text, punkte, antw)
values ('Thomas ist bereit, mitzureisen', 0, null );
--11
insert into frage (text, punkte, antw)
values ('Suchen Sie alle Bewohner (nur ihre Id und Name erforderlich),' ||
        'die als Schiffbauer arbeiten.'
        , 10, 'select id, name from bewohner where beruf = \"Schiffbauer\"');
--12
insert into frage (text, punkte, antw)
values ('Suchen Sie die Schiffbauer, die altes Schiff mit dem Status zu verkaufen haben.' ||
        'Hinweis : die Spalten für die Anfrage sind : bewohner.id, bewohner.name, produkt.name as produkt,' ||
        'produkt.preis, produkt.menge, produkt.status',
        10,'select bewohner.id, bewohner.name, produkt.name as produkt,produkt.preis,produkt.menge,produkt.status ' ||
           'from bewohner, produkt where bewohner.id = produkt.besitzer_id ' ||
           'and produkt.name =\"altes Schiff\" and produkt.status = \"zu verkaufen\"');
insert into frage (text, punkte, antw)
values ('Markus ist bereit, sein altes Schiff zu verkaufen. ' ||
        'Nach der Untersuchung brauchen Sie für das Schiff neuen Segel und Steuerrad.' ||
        'Suchen Sie die Bewohner (nur ihre name, produkt_name und preis erforderlich), ' ||
        'die entweder Segel oder Steuerrad zu verkaufen haben', 10,
        'select bewohner.name, produkt.name as produkt_name, produkt.preis from bewohner, produkt ' ||
        'where bewohner.id = produkt.besitzer_id ' ||
        'and (produkt.name = \"Segel\" or produkt.name = \"Steuerrad\") ' ||
        'and produkt.status = \"zu verkaufen\"');
insert into frage (text, punkte, antw)
values ('Jetzt brauchen Sie Lebensmittel für die Reise', 0, null);
insert into frage (text, punkte, antw)
values ('Finden Sie heraus, was ist der durchschnittliche Preis aller Produkte aus der Tabelle produkt'
, 10, 'select avg(preis) from produkt');
insert into frage (text, punkte, antw)
values ('Finden Sie heraus, was ist der durchschnittliche Preis aller Produkte jeder Kategorie. ' ||
        'Ordnen Sie nach Preis aufsteigend. ' ||
        'Hinweis : die Antwort hat 2 Spalten : kategorie, preis_durchschnitt.'
       , 10, 'select kategorie, avg(preis) as preis_durchschnitt from produkt group by kategorie order by preis_durchschnitt');
insert into frage(text, punkte, antw)
values ('Got sei Dank ! Der Preis der Lebensmitteln ist am niedrigsten !', 0, null );
insert into frage (text, punkte, antw)
values ('Wenn Sie alle Lebensmitteln kaufen würde, wie viel kostet es ?.' ||
        'Hinweis : die Antwort hat eine Spalte : summe', 10,
        'select sum(preis * menge) as summe from produkt' );
insert into frage(text, punkte, antw)
values ('Oh ! Das ist dann zu viel', 0, null );
insert into frage (text, punkte, antw)
values ('Suchen Sie die Bewohner, die Fleisch zu verkaufen haben.' ||
        'Hinweis : Antwort hat 4 Spalten : name, fleischsorte, preis, menge ', 10,
        'select bewohner.name, produkt.name as fleischsorte, produkt.preis, produkt.menge from bewohner, produkt ' ||
        'where bewohner.id = produkt.besitzer_id and produkt.name like \"%fleisch\" and produkt.status = \"zu verkaufen\"' );
insert into frage (text, punkte, antw)
values ('Jetzt brauchen Sie noch Reis, Mehl und Kartoffel.' ||
        'Suchen Sie alle Bewohner, die diese Produkte zu verkaufen haben.' ||
        'Hinweis: Antwort hat 4 Spalten : name , produkt, preis, menge', 10,
        'select bewohner.name, produkt.name as produkt, preis, menge from bewohner, produkt' ||
        ' where bewohner.id = produkt.besitzer_id ' ||
        'and (produkt.name = \"Reis\" or produkt.name = \"Mehl\" or produkt.name = \"Kartoffel\")');
insert into frage (text, punkte, antw)
values ('Ihr Schiff ist aufgeladen, Sie können die Reise endlich beginnen !!!',10,null );
insert into frage (text, punkte, antw)
values ('Die Navigation sagt, die nächste Insel liegt im Norden. Finden Sie alle Inseln im Norden.' ||
        'Hinweis : die Antwort hat nur 2 Spalten : id und name der Insel', 10,
        'select insel.id, insel.name from insel, koordinaten where breite_richtung = \"N\" and insel.koord_id = koordinaten.id ');
insert into frage (text, punkte, antw)
values ('Nächte Information : die Insel liegt im NW , zeigen Sie diese Inseln an ', 10,
        'select insel.id, insel.name from insel, koordinaten where breite_richtung = \"N\"' ||
        ' and laenge_richtung = \"W\" and insel.koord_id = koordinaten.id');
insert into frage (text, punkte, antw)
values ('Die nächste Insel ist eine dieser 2 Insel, welche die kleiner Abstand hat. Zeigen Sie die name und koordinaten dieser Insel an.' ||
        'Hinweis : die Spalten der Antwort sind : name, abstand, breite_grad, breite_richtung, laenge_grad, laenge_richtung', 10,
        'select name, abstand,breite_grad,breite_richtung, laenge_grad,laenge_richtung from insel, koordinaten ' ||
        'where insel.koord_id = koordinaten.id and (insel.id = 2 or insel.id = 5) ' ||
        'and insel.abstand = (select min(abstand) from insel where id = 2 or id = 5)');
insert into frage (text, punkte, antw)
values ('Alles klar ! Die Insel Ohara ist unser nächstes Ziel !', 10, null );
insert into frage (text, punkte, antw)
values ('Auf dem Weg zum Ziel muss der Navigator immer auf das Wetter aufpassen. ' ||
        'Wenn die Feuchtigkeit größer als 85% und Windgeschwindigkeit größer als 50km/h, ' ||
        'ist es höchst wahrscheinlich, dass es da bald ein Sturm kommt.' ||
        'Das Schiff ist in 2 Stunden ungefähr in Koordinaten 20°N 80°W. ' ||
        'Checken Sie das Wetter dort (am 2021-11-21) ', 10,
        'select breite_grad, breite_richtung , laenge_grad, laenge_richtung, feuchtigkeit, wind_geschw ' ||
        'from koordinaten, wetter ' ||
        'where koord_id = koordinaten.id and breite_grad = 20 and breite_richtung = \"N\" ' ||
        'and laenge_grad = 80 and laenge_richtung = \"W\" ' ||
        'and zeit = \"2021-11-21\"');
insert into frage (text, punkte, antw)
values ('Oh, da kommt bald ein Sturm , wir müssen umfahren', 0, null );
insert into frage (text, punkte, antw)
values ('Sie sind auf Insel Ohara gekommen haben aber kein Gold mehr um Lebensmitteln zu kaufen.' ||
        'Alle müssen arbeiten, um Gold zu verdienen. Sie sollen für jeden ihrer Mannschaft einen Job suchen', 0, null );
insert into frage (text, punkte, antw)
values ('Finden Sie das durchschnittliche Einkommen einzelner Berufsgruppe auf der Insel, geordnet nach dem absteigend Einkommen' ||
        'Hinweis : Antwort hat 2 Spalten : beruf und avg_einkommen', 10,
        'select beruf, avg(einkommen) as avg_einkommen from insel, bewohner ' ||
        'where insel.name = \"Ohara\" and bewohner.insel_id = insel.id ' ||
        'group by beruf order by avg_einkommen desc');
insert  into frage (text, punkte, antw)
values ('Da verdient Musiker am meistens', 0, null);
insert  into frage (text, punkte, antw)
values ('Suchen Sie den am bestens bezahlten Musiker, dann kann dein Musiker bei ihm arbeiten' ||
        'Hinweis : sein id, name und einkommen sind erforderlich', 10,
        'select bewohner.id, bewohner.name, einkommen from bewohner, insel where bewohner.insel_id = insel.id and beruf =\"Musiker\"
        and einkommen = (select max (einkommen)  from bewohner where beruf = \"Musiker\")');
insert into frage (text, punkte, antw)
values ('Für den Koch suchen Sie jemanden, der Koch oder Baecker ist ' ||
        'Hinweis : Die Antwort hat 3 Spalten: bewohner.id, bewohner.name, bewohner.beruf.', 10,
        'select bewohner.id, bewohner.name, bewohner.beruf from bewohner, insel ' ||
        'where insel.name = \"Ohara\" and bewohner.insel_id = insel.id ' ||
        'and (beruf = \"Koch\" or beruf = \"Baecker\"');
insert into frage (text, punkte, antw)
values ('Der Koch kann bei dem Baecker Luca arbeiten',0,null );
insert into frage (text, punkte, antw)
values ('Jetzt bleibt noch der Navigator ',0,null );
insert into frage (text, punkte, antw)
values ('Finden Sie, wie viel Navigator auf der Insel Ohara es gibt ?',10,
        'select count (*) from insel, bewohner where insel.name = \"Ohara\" and insel.id = bewohner.insel_id and beruf = \"Navigator\"');
insert into frage (text, punkte, antw)
values ('Ein Navigator sucht nach einer Aushilfe. Sein Einkommen ist unter dem durchschnittlichen Einkommen aller Bewohner der Insel' ||
        'Hinweis : nur seine id und name erforderlich',10,
        'select bewohner.id, bewohner.name from insel, bewohner ' ||
        'where insel.name = \"Ohara\" and insel.id = bewohner.insel_id and bewohner.beruf = \"Navigator\" ' ||
        'and einkommen < (select avg(einkommen) from bewohner, insel where insel.name = \"Ohara\" and bewohner.insel_id = insel.id)');
insert into frage (text, punkte, antw)
values ('Sehr Gut ! Alle haben jetzt einen Job', 0, null );
insert into frage (text, punkte, antw)
values ('Auf dem See ist man leicht, krank zu werden. Sie brauchen einen Arzt für seine Mannschaft', 0, null );
insert into frage (text, punkte, antw)
values ('Ordnen Sie erstmal die Bewohner nach dem beruf IQ - Ranking der Bewohner jeder Gruppe ' ||
        'Hinweis : Benutzen Sie  window, rank() function ' ||
        'Die Spalten der Antwort sind : bewohner.id, bewohner.name, beruf , IQ, IQ_Ranking', 10,
        'select bewohner.id, bewohner.name, beruf, IQ, rank() over w as IQ_Ranking from bewohner, insel where bewohner.insel_id = insel.id' ||
        'and insel.name = \"Ohara\"
         window w as (partition by beruf order by IQ desc)' );
insert into frage (text, punkte, antw)
values ('Hier erkennen Sie , dass es keinen Arzt mit dem Platz 2 gibt. ' ||
        'Da zwei Ärzte am 1.Platz sind. Sie können das Problem lösen. Anstatt rank() benutzen Sie bitte dense_rank()',
        10, 'select bewohner.id, bewohner.name, beruf, IQ, dense_rank() over w as IQ_Ranking from bewohner, insel where bewohner.insel_id = insel.id'' ||
        ''and insel.name = \"Ohara\"
         window w as (partition by beruf order by IQ desc)' );
insert into frage (text, punkte, antw)
values ('Der Arzt, wer bereit ist, mitzukommen, hat IQ Ranking 3 in seiner Gruppe. Finden Sie ihn' ||
        'Empfehlen : benutzen Sie window und dense_rank() function' ||
        'Hinweis : Antwort hat die Spalten : id, name, iq und beruf', 10,
        'select	 id, name, iq, beruf
         from	 (select bewohner.id, bewohner.name, beruf, IQ, dense_rank() over w as IQ_Ranking from bewohner, insel where bewohner.insel_id = insel.id
	             and insel.name = \"Ohara\"
	             window w as (partition by beruf order by IQ desc)) as iq_tabelle_ranking
         where   iq_ranking = 3 and beruf = \"Arzt\"');
insert into frage (text, punkte, antw)
values ('Sehr gut ! Ihre Mannschaft hat jetzt genug Gold, um das Schiff wieder bereit zu machen' ||
        'Jetzt müssen Sie die nächste Insel finden', 0, null );
insert into frage (text, punkte, antw)
values ('Die nächste Insel liegt auch im NW und hat Breitengrad und Längengrad gleich 80. Finden Sie sie.' ||
        'Hinweis : Spalten der Antwort : insel.name, breite_grad, breite_richtung, laenge_grad, laenge_richtung'
        ,10, 'select insel.name, breite_grad, breite_richtung, laenge_grad, laenge_richtung ' ||
             'from insel, koordinaten ' ||
             'where koordinaten.id = insel.koord_id and breite_richtung = \"N\" and laenge_richtung = \"W\"' ||
             'and breite_grad = laenge_grad');
insert into frage (text, punkte, antw)
values ('Ok, die nächste Insel ist Konomi !', 0, null );
insert into frage (text, punkte, antw)
values ('Auf der Insel Konomi herrscht ein Diktatur. Die  Revolteure haben ihre Mannschaft gebeten, sie anzuschließen, um diesen Diktator zu stürzen. ' ||
        'Obwohl das Volk in Armut lebt , hat der Diktator ein Luxusleben. Sie sollten sein Luxusleben enthüllen', 0, null );
insert into frage (text, punkte, antw)
values ('Wie viele Hersteller des Bekanntheitsgrades 5 gibt es ?', 10, 'select count(*) from hersteller where bekanntheitsgrad = 5');
insert into frage (text , punkte, antw)
values ('Wie oft hat er im 01.2021 Produkte von einem Hersteller des Bekanntheitsgrades größer als 4 gekauft' ||
        'Hinweis : Antwort hat eine Spalte bestellung_anzahl.', 10,
        'select count (*) as bestellung_anzahl from kaeufe, ware, hersteller where kaeufe.ware_id = ware.id and ware.hersteller_id = hersteller.id
                and tag <= \"2021-01-31\" and tag >= \"2021-01-01\" and hersteller.bekanntheitsgrad >= 4');
insert into frage (text, punkte, antw)
values ('Wie oft hat er im 01.2021 Produkte von einem Hersteller des Bekanntheitsgrades größer als 3 gekauft?', 10,
        'select count (*) from kaeufe where tag >= \"2021-01-01\"  and tag <= \"2021-01-31\"');
insert into frage (text, punkte, antw)
values ('Wie ist der Kaufbetrag im 01.2021 ?' , 10,
        'select sum(anzahl * preis) as Kaufbetrag from kaeufe, ware ' ||
        'where kaeufe.ware_id = ware.id and tag >= \"2021-01-01\"  and tag <= \"2021-01-31\"');
insert into frage (text, punkte, antw)
values ('Was ist der höchste Betrag, den er am einem Tag gekauft hat ?' ||
        'Hinweis : Die Antwort hat die einzige Spalte max_betrag', 10,
        'select max (tag_kaufbetrag) as max_betrag from
        (select sum(anzahl * preis) as tag_kaufbetrag from kaeufe, ware where kaeufe.ware_id = ware.id group by tag) as tmp');
insert into frage (text, punkte, antw)
values ('Was ist der durchschnittliche Kaufbetrag jeden Tag vom 01.01.2021 bis 31.01.2021 ?' ||
        'Hinweis : die Antwort hat eine Spalte kaufbetrag_durchschnitt', 10,
        'select avg (kaufbetrag_pro_tag) from
	        (select sum(preis*anzahl) as kaufbetrag_pro_tag
	        from kaeufe, ware where kaeufe.ware_id = ware.id
	        and tag <= \"2021-01-31\" and tag >= ''2021-01-01\" group by tag) as tmp');
insert into frage (text, punkte, antw)
values ('Am welchen Tag hat der mehr als Durchschnitt gekauft ? ' ||
        'Hinweis: die Antwort hat 2 Spalten : tag, kaufbetrag_pro_tag', 10,
        'with kaufbetrag as (select tag, sum(preis*anzahl) as kaufbetrag_pro_tag from kaeufe, ware where kaeufe.ware_id = ware.id group by tag)
            select *  from kaufbetrag where kaufbetrag_pro_tag > (select avg(kaufbetrag_pro_tag) from kaufbetrag)');
insert into frage (text, punkte, antw)
values ('Mit ihrer Hilfe wurde der Diktator gestürzt. ' ||
        'Jetzt können Sie mit der Reise weiter. ' ||
        'Auf der  Insel gibt es den Schatz ,der für jede Reisende ein Traum ist.', 0, null );
insert into frage (text, punkte, antw)
values ('Es gibt einen Bewohner, der weiß, wo der Schatz zu finden ist.' ||
        'Antworten Sie folgende Fragen, um ihn zu finden', 0 , null );
insert into frage (text, punkte, antw)
values ('Was ist das dominante Hobby der Bewohner auf der Insel ?' ||
        'Hinweis : die Antwort hat 2 Spalten : hobby, interessierte (für die Anzahl der Interessierte)', 10,
        'with hobby_statistik as
        (select hobby, count (*) as interessierte from insel, bewohner where insel.id = bewohner.insel_id and insel.id = 2 group by hobby)
        select hobby, interessierte from hobby_statistik where interessierte = (select max(interessierte) from hobby_statistik)');
insert into frage (text, punkte, antw)
values ('Wer von Ihnen kann sein Hobby Archaeologie zum Beruf machen ? ' ||
        'Zeigen Sie die Liste dieser Bewohner mit ihrer id und ihrem name an.',10,
        '');

