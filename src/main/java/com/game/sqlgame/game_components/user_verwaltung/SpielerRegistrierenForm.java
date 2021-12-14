package com.game.sqlgame.game_components.user_verwaltung;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SpielerRegistrierenForm {

    @NotNull
    private String name ="";

    @NotNull
    private String passwort="";

    @NotNull
    private String passwortWiederholung = "";
}
