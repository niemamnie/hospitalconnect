package de.hackathon.hospitalconnect.security.providers;

import de.hackathon.hospitalconnect.exceptions.intern.NotFoundException;
import de.hackathon.hospitalconnect.model.user.User;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import de.hackathon.hospitalconnect.security.exceptions.InvalidCredentialsException;
import de.hackathon.hospitalconnect.service.PasswordEncryptionService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncryptionService passwordEncryptionService;

    public MySQLAuthenticationProvider(UserRepository userRepository, PasswordEncryptionService passwordEncryptionService) {
        this.userRepository = userRepository;
        this.passwordEncryptionService = passwordEncryptionService;
    }


    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();


        User foundUser = userRepository.findByCredentials_Email(email)
                .orElseThrow(() ->
                        new NotFoundException("Email"));
        if (passwordEncryptionService.match(password, foundUser.getCredentials().getPassword())) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            foundUser.getRoles().size();
            foundUser.getRoles().forEach(role -> {
                authorityList.add(new SimpleGrantedAuthority(role.toString()));
            });
            return new UsernamePasswordAuthenticationToken(email, password, authorityList);
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
