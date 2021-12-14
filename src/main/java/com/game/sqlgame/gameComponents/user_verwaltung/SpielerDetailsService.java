package com.game.sqlgame.gameComponents.user_verwaltung;

import com.game.sqlgame.service.SpielerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpielerDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SpielerDetailsService.class);

    private final SpielerService spielerService;

    public SpielerDetailsService(SpielerService spielerService) {
        this.spielerService = spielerService;
    }

    @Override
    public AktuellerSpieler loadUserByUsername(String spielerName) throws UsernameNotFoundException {

        Spieler spieler = spielerService.getPlayerByName(spielerName).orElseThrow(() ->
            new UsernameNotFoundException("Spieler mit dem Name "+ spielerName + "nicht gefunden")
        );

        log.info(spieler.toString());
        return new AktuellerSpieler(spieler);
    }
}
