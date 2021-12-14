package com.game.sqlgame.gameComponents.user_verwaltung.registrierung;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegistrierungForm {

    @NotNull
    private String name ="";

    @NotNull
    private String passwort="";

    @NotNull
    private String passwortWiederholung = "";
}
