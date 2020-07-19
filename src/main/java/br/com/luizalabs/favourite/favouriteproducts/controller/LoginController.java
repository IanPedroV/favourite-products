package br.com.luizalabs.favourite.favouriteproducts.controller;

import br.com.luizalabs.favourite.favouriteproducts.config.security.ClientDetailsService;
import br.com.luizalabs.favourite.favouriteproducts.service.JwtService;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.LoginForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.RequestPasswordForm;
import br.com.luizalabs.favourite.favouriteproducts.model.JwtResponse;
import br.com.luizalabs.favourite.favouriteproducts.service.EmailSenderService;
import br.com.luizalabs.favourite.favouriteproducts.service.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final ClientDetailsService clientDetailsService;
    private final JwtService jwtTokenUtil;
    private final EmailSenderService emailSenderService;
    private final PasswordService passwordService;

    public LoginController(AuthenticationManager authenticationManager, ClientDetailsService clientDetailsService,
                           JwtService jwtTokenUtil, EmailSenderService emailSenderService, PasswordService passwordService) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.emailSenderService = emailSenderService;
        this.passwordService = passwordService;
    }

    @PostMapping("/requestpassword")
    public ResponseEntity<?> requestPassword(@RequestBody RequestPasswordForm loginForm) {
        Integer temporaryPassword = passwordService.generatePassword();
        passwordService.add(loginForm.getEmail(), temporaryPassword);
        emailSenderService.sendMail(loginForm.getEmail(), temporaryPassword);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> createAuthentication(@RequestBody LoginForm loginForm) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(500).build();
        }
        final UserDetails userDetails = clientDetailsService.loadUserByUsername(loginForm.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        passwordService.remove(loginForm.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
