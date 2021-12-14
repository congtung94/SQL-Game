package com.game.sqlgame.game_components.user_verwaltung;

import com.game.sqlgame.service.SpielerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SpielerRegistrierenValidator implements Validator {
    private final static Logger log = LoggerFactory.getLogger(SpielerRegistrierenValidator.class);
    private final SpielerService spielerService;

    public SpielerRegistrierenValidator(SpielerService spielerService) {
        this.spielerService = spielerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SpielerRegistrierenForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SpielerRegistrierenForm form = (SpielerRegistrierenForm) target;
        validateName(errors, form);
        validatePassword(errors, form);
        log.info("#29 errors count = " + errors.getErrorCount());
    }

    private void validatePassword (Errors errors, SpielerRegistrierenForm form){
        if (!form.getPasswort().equals(form.getPasswortWiederholung())){
            errors.reject("password", "unterschiedliche Passworte eingeben");
            log.info("unterschiedliche Passworte eingegeben !!!");
        }
    }

    private void validateName (Errors errors, SpielerRegistrierenForm form){
        if (spielerService.existsByName(form.getName())){
            errors.reject("name", "der Username ist schon vergeben");
        }
    }
}
