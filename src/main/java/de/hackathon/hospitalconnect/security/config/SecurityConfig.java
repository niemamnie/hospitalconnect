package de.hackathon.hospitalconnect.security.config;

import de.hackathon.hospitalconnect.security.providers.MySQLAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(MySQLAuthenticationProvider mySQLAuthenticationProvider) {
        this.mySQLAuthenticationProvider = mySQLAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin().permitAll().and().logout().permitAll().and().csrf().disable();
    }


    final MySQLAuthenticationProvider mySQLAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(mySQLAuthenticationProvider);
    }


}
