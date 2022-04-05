package com.game.sqlgame.controller;


import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.model.Spieler;
import com.game.sqlgame.gameComponents.user_verwaltung.registrierung.RegistrierungForm;
import com.game.sqlgame.gameComponents.user_verwaltung.registrierung.RegistrierungValidator;
import com.game.sqlgame.repository.SpielerRepository;
import com.game.sqlgame.repository.SpielstandRepository;
import com.game.sqlgame.repository.UbersprungenFragenRepository;
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

    private final SpielerRepository spielerRepository;
    private final SpielstandRepository spielstandRepository;
    private final UbersprungenFragenRepository ubersprungenFragenRepository;

    public LoginController(RegistrierungValidator validator, SpielerRepository spielerRepository,
                           SpielstandRepository spielstandRepository, UbersprungenFragenRepository ubersprungenFragenRepository) {
        this.validator = validator;
        this.spielerRepository = spielerRepository;
        this.spielstandRepository = spielstandRepository;

        this.ubersprungenFragenRepository = ubersprungenFragenRepository;
    }

    @InitBinder("registrierenForm")
    public void initBinder (WebDataBinder webDataBinder){
        webDataBinder.addValidators(validator);
    }

    @GetMapping("/registration")
    public String registrieren (){
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String registrieren (@Valid @ModelAttribute("registrierenForm") RegistrierungForm form,
                                BindingResult bindingResult, Model model, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            log.info(bindingResult.getGlobalError().getDefaultMessage());
            return "registrationPage";
        }
        Spieler spieler = new Spieler();
        spieler.setName(form.getName().toLowerCase());
        spieler.setPasswort(form.getPasswort());
        spielerRepository.save(spieler);

        int spielerId = spielerRepository.getPlayerByName(spieler.getName()).get().getId();

        Spielstand spielstand = new Spielstand();
        spielstand.setSpielerId(spielerId);
        spielstandRepository.save(spielstand);


        authWithHttpServletRequest(request, spieler.getName(), spieler.getPasswort());

        return "redirect:/playing";
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
