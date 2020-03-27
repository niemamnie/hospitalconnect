package de.hackathon.hospitalconnect.security.providers;

import de.hackathon.hospitalconnect.model.user.User;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import de.hackathon.hospitalconnect.security.exceptions.InvalidCredentialsException;
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

    public MySQLAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User foundUser = userRepository.getByCredentials_EmailAndCredentials_Password(email, password)
                .orElseThrow(InvalidCredentialsException::new);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        foundUser.getRoles().size();
        foundUser.getRoles().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority(role.toString()));
        });
        return new UsernamePasswordAuthenticationToken(email, password, authorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
