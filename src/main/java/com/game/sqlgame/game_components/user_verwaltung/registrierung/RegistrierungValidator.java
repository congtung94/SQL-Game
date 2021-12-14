package com.game.sqlgame.game_components.user_verwaltung.registrierung;

import com.game.sqlgame.service.SpielerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrierungValidator implements Validator {
    private final static Logger log = LoggerFactory.getLogger(RegistrierungValidator.class);
    private final SpielerService spielerService;

    public RegistrierungValidator(SpielerService spielerService) {
        this.spielerService = spielerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegistrierungForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrierungForm form = (RegistrierungForm) target;
        validateName(errors, form);
        validatePassword(errors, form);
        log.info("#29 errors count = " + errors.getErrorCount());
    }

    private void validatePassword (Errors errors, RegistrierungForm form){
        if (!form.getPasswort().equals(form.getPasswortWiederholung())){
            errors.reject("password", "unterschiedliche Passworte eingeben");
            log.info("unterschiedliche Passworte eingegeben !!!");
        }
    }

    private void validateName (Errors errors, RegistrierungForm form){
        if (spielerService.existsByName(form.getName())){
            errors.reject("name", "der Username ist schon vergeben");
        }
    }
}
