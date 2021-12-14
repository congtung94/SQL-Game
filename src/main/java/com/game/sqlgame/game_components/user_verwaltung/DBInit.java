package com.game.sqlgame.game_components.user_verwaltung;

import com.game.sqlgame.game_components.Spielstand;
import com.game.sqlgame.repository.SpielerRepository;
import com.game.sqlgame.repository.SpielstandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBInit {

    private final static Logger log = LoggerFactory.getLogger(DBInit.class);

    private final SpielerRepository spielerRepository;
    private final SpielstandRepository spielstandRepository;


    public DBInit(SpielerRepository spielerRepository, SpielstandRepository spielstandRepository) {
        this.spielerRepository = spielerRepository;
        this.spielstandRepository = spielstandRepository;

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
        spielstandMai.setAktuelleFrageId(4);
        spielstandRepository.save(spielstandMai);

        Spielstand spielstandNga = new Spielstand();
        spielstandNga.setLevel(2);
        spielstandNga.setSpielerId(3);
        spielstandNga.setPunkte(40);
        spielstandNga.setZeit(10);
        spielstandNga.setAktuelleFrageId(2);
        spielstandRepository.save(spielstandNga);


    }
}
