package com.game.sqlgame.gameComponents.security;

import com.game.sqlgame.gameComponents.user_verwaltung.SpielerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SpielerDetailsService spielerDetailsService;

    public WebSecurityConfig(SpielerDetailsService spielerDetailsService) {
        this.spielerDetailsService = spielerDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*.authorizeRequests()
                .anyRequest().authenticated()
                .and()*/
                .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/spielen",true)
                    .passwordParameter("password")
                    .usernameParameter("name")
                .and()
                .logout()
                    /*.logoutUrl("/logout")*/
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/");

    }
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth
                .userDetailsService(userDetailsService());
        //auth.jdbcAuthentication().passwordEncoder(passwordEncoder());
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder();
    }
}
