package com.game.sqlgame.controller;

import com.game.sqlgame.model.UbersprungenFragen;
import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.model.Spieler;
import com.game.sqlgame.gameComponents.user_verwaltung.registrierung.RegistrierungForm;
import com.game.sqlgame.gameComponents.user_verwaltung.registrierung.RegistrierungValidator;
import com.game.sqlgame.service.UbersprungenFragenService;
import com.game.sqlgame.service.SpielerService;
import com.game.sqlgame.service.SpielstandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final RegistrierungValidator validator;
    private final SpielerService spielerService;
    private final SpielstandService spielstandService;
    private final UbersprungenFragenService spielKomponenteService;

    public LoginController(RegistrierungValidator validator, SpielerService spielerService, SpielstandService spielstandService, UbersprungenFragenService spielKomponenteService) {
        this.validator = validator;
        this.spielerService = spielerService;
        this.spielstandService = spielstandService;
        this.spielKomponenteService = spielKomponenteService;
    }

    @InitBinder("registrierenForm")
    public void initBinder (WebDataBinder webDataBinder){
        webDataBinder.addValidators(validator);
    }

    @GetMapping("/registrieren")
    public String registrieren (){
        return "registrieren";
    }

    @PostMapping("/registrieren")
    public String registrieren (@Valid @ModelAttribute("registrierenForm") RegistrierungForm form,
                                BindingResult bindingResult, Model model, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            log.info(bindingResult.getGlobalError().getDefaultMessage());
            return "registrieren";
        }
        Spieler spieler = new Spieler();
        spieler.setName(form.getName());
        spieler.setPasswort(form.getPasswort());
        spielerService.save(spieler);

        int spielerId = spielerService.getPlayerByName(spieler.getName()).get().getId();

        Spielstand spielstand = new Spielstand();
        spielstand.setSpielerId(spielerId);
        spielstandService.save(spielstand);

        UbersprungenFragen spielKomponente = new UbersprungenFragen();
        spielKomponente.setSpieler_id(spielerId);
        spielKomponenteService.save(spielKomponente);

        authWithHttpServletRequest(request, spieler.getName(), spieler.getPasswort());

        return "redirect:/spielen";
    }

    // https://www.baeldung.com/spring-security-auto-login-user-after-registration
    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login ", e);
        }
    }
}
