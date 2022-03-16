package com.game.sqlgame.gameComponents.user_verwaltung.registrierung;

import com.game.sqlgame.repository.SpielerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrierungValidator implements Validator {
    private final static Logger log = LoggerFactory.getLogger(RegistrierungValidator.class);
    private final SpielerRepository spielerRepository;

    public RegistrierungValidator(SpielerRepository spielerRepository) {
        this.spielerRepository = spielerRepository;
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
        if (spielerRepository.existsByName(form.getName())){
            errors.reject("name", "der Username ist schon vergeben");
        }
    }
}
