package com.game.sqlgame.gameComponents.user_verwaltung;

import com.game.sqlgame.model.Spieler;
import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.model.UbersprungenFragen;
import com.game.sqlgame.repository.SpielerRepository;
import com.game.sqlgame.repository.SpielstandRepository;
import com.game.sqlgame.repository.UbersprungenFragenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBInit {

    private final static Logger log = LoggerFactory.getLogger(DBInit.class);

    private final SpielerRepository spielerRepository;
    private final SpielstandRepository spielstandRepository;
    private final UbersprungenFragenRepository ubersprungenFragenRepository;


    public DBInit(SpielerRepository spielerRepository, SpielstandRepository spielstandRepository, UbersprungenFragenRepository ubersprungenFragenRepository) {
        this.spielerRepository = spielerRepository;
        this.spielstandRepository = spielstandRepository;
        this.ubersprungenFragenRepository = ubersprungenFragenRepository;
    }

    @PostConstruct
    public void init (){

        Spieler tung = new Spieler();
        tung.setName("tung");
        tung.setPasswort("demo");
        spielerRepository.save(tung);
        log.info(spielerRepository.getPlayerByName("tung").toString());

        Spieler mai = new Spieler();
        mai.setName("mai");
        mai.setPasswort("demo");
        spielerRepository.save(mai);

        Spieler nga = new Spieler();
        nga.setName("nga");
        nga.setPasswort("demo");
        spielerRepository.save(nga);

        Spieler phan = new Spieler();
        phan.setName("phan");
        phan.setPasswort("demo");
        spielerRepository.save(phan);

        Spieler dong = new Spieler();
        dong.setName("dong");
        dong.setPasswort("demo");
        spielerRepository.save(dong);

        Spieler teo = new Spieler();
        teo.setName("teo");
        teo.setPasswort("demo");
        spielerRepository.save(teo);

        Spieler tien = new Spieler();
        tien.setName("tien");
        tien.setPasswort("demo");
        spielerRepository.save(tien);

        Spieler duong = new Spieler();
        duong.setName("duong");
        duong.setPasswort("demo");
        spielerRepository.save(duong);

        Spieler cu = new Spieler();
        cu.setName("cu");
        cu.setPasswort("demo");
        spielerRepository.save(cu);

        Spielstand spielstandTung = new Spielstand();
        spielstandTung.setLevel(1);
        spielstandTung.setSpielerId(1);
        spielstandTung.setPunkte(0);
        spielstandTung.setZeit(0);
        spielstandTung.setAktuelleFrageId(1);
        spielstandRepository.save(spielstandTung);


        Spielstand spielstandMai = new Spielstand();
        spielstandMai.setLevel(3);
        spielstandMai.setSpielerId(2);
        spielstandMai.setPunkte(50);
        spielstandMai.setZeit(20);
        spielstandMai.setAktuelleFrageId(1);
        spielstandRepository.save(spielstandMai);

        Spielstand spielstandNga = new Spielstand();
        spielstandNga.setLevel(2);
        spielstandNga.setSpielerId(3);
        spielstandNga.setPunkte(40);
        spielstandNga.setZeit(10);
        spielstandNga.setAktuelleFrageId(1);
        spielstandRepository.save(spielstandNga);

        Spielstand spielstandPhan = new Spielstand();
        spielstandPhan.setLevel(2);
        spielstandPhan.setSpielerId(4);
        spielstandPhan.setPunkte(50);
        spielstandPhan.setZeit(100);
        spielstandPhan.setAktuelleFrageId(6);
        spielstandRepository.save(spielstandPhan);

        Spielstand spielstandDong = new Spielstand();
        spielstandDong.setLevel(2);
        spielstandDong.setSpielerId(5);
        spielstandDong.setPunkte(90);
        spielstandDong.setZeit(2000);
        spielstandDong.setAktuelleFrageId(10);
        spielstandRepository.save(spielstandDong);

        Spielstand spielstandTeo = new Spielstand();
        spielstandTeo.setLevel(3);
        spielstandTeo.setSpielerId(6);
        spielstandTeo.setPunkte(90);
        spielstandTeo.setZeit(2000);
        spielstandTeo.setAktuelleFrageId(10);
        spielstandRepository.save(spielstandTeo);

        Spielstand spielstandTien = new Spielstand();
        spielstandTien.setLevel(3);
        spielstandTien.setSpielerId(7);
        spielstandTien.setPunkte(140);
        spielstandTien.setZeit(3000);
        spielstandTien.setAktuelleFrageId(15);
        spielstandRepository.save(spielstandTien);

        Spielstand spielstandDuong = new Spielstand();
        spielstandDuong.setLevel(2);
        spielstandDuong.setSpielerId(8);
        spielstandDuong.setPunkte(90);
        spielstandDuong.setZeit(3001);
        spielstandDuong.setAktuelleFrageId(11);
        spielstandRepository.save(spielstandDuong);

        Spielstand spielstandCu = new Spielstand();
        spielstandCu.setLevel(3);
        spielstandCu.setSpielerId(9);
        spielstandCu.setPunkte(200);
        spielstandCu.setZeit(3001);
        spielstandCu.setAktuelleFrageId(48);
        spielstandRepository.save(spielstandCu);

        UbersprungenFragen fragen = new UbersprungenFragen(9, 7);
        /*UbersprungenFragen fragen1 = new UbersprungenFragen(9, 16);
        UbersprungenFragen fragen2 = new UbersprungenFragen(9, 25);
        UbersprungenFragen fragen3 = new UbersprungenFragen(9, 30);*/
        ubersprungenFragenRepository.save(fragen);
        /*ubersprungenFragenRepository.save(fragen1);
        ubersprungenFragenRepository.save(fragen2);
        ubersprungenFragenRepository.save(fragen3);*/

    }
}
