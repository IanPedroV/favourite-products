package br.com.luizalabs.favourite.favouriteproducts.controller;


import br.com.luizalabs.favourite.favouriteproducts.config.security.ClientDetailsService;
import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.LoginForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.RequestPasswordForm;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.service.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private ClientDetailsService clientDetailsService;
    @Mock
    private JwtService jwtTokenUtil;
    @Mock
    private EmailSenderService emailSenderService;
    @Mock
    private PasswordService passwordService;

    @Test
    void shouldRequestPassword() {
        Mockito.when(passwordService.generatePassword()).thenReturn(123123);
        ResponseEntity<?> response = loginController.requestPassword(new RequestPasswordForm());
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

   @Test
    void shouldCreateAuthentication() {
        ResponseEntity<?> response = loginController.createAuthentication(new LoginForm("user1@gmail.com", "pwd1"));
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
    }


}

