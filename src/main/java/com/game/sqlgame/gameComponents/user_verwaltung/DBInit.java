package com.game.sqlgame.gameComponents.user_verwaltung;

import com.game.sqlgame.model.SpielKomponente;
import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.model.Spieler;
import com.game.sqlgame.repository.SpielKomponenteRepository;
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
    private final SpielKomponenteRepository spielKomponenteRepository;


    public DBInit(SpielerRepository spielerRepository, SpielstandRepository spielstandRepository, SpielKomponenteRepository spielKomponenteRepository) {
        this.spielerRepository = spielerRepository;
        this.spielstandRepository = spielstandRepository;
        this.spielKomponenteRepository = spielKomponenteRepository;
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

        SpielKomponente spielKomponenteTung = new SpielKomponente();
        spielKomponenteTung.setSpieler_id(1);
        spielKomponenteRepository.save(spielKomponenteTung);

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


    }
}
