package global.geoguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import global.geoguard.model.Credentials;
import global.geoguard.model.Token;
import global.geoguard.model.User;
import global.geoguard.service.AuthService;
import global.geoguard.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) {
        User user = (User) authService.loadUserByUsername(credentials.email());
        if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }
        return tokenService.createToken(user);
    }

}
