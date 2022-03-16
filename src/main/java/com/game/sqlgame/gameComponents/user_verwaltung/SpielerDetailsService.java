package com.game.sqlgame.gameComponents.user_verwaltung;

import com.game.sqlgame.model.Spieler;
import com.game.sqlgame.repository.SpielerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpielerDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SpielerDetailsService.class);

    private final SpielerRepository spielerRepository;

    public SpielerDetailsService(SpielerRepository spielerRepository) {
        this.spielerRepository = spielerRepository;
    }

    @Override
    public AktuellerSpieler loadUserByUsername(String spielerName) throws UsernameNotFoundException {

        Spieler spieler = spielerRepository.getPlayerByName(spielerName).orElseThrow(() ->
            new UsernameNotFoundException("Spieler mit dem Name "+ spielerName + "nicht gefunden")
        );

        log.info(spieler.toString());
        return new AktuellerSpieler(spieler);
    }
}
