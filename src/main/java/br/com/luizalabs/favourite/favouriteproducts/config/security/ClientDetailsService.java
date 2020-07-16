package br.com.luizalabs.favourite.favouriteproducts.config.security;

import br.com.luizalabs.favourite.favouriteproducts.service.PasswordService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientDetailsService implements UserDetailsService {


    private final PasswordService passwordService;

    public ClientDetailsService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new User(email, String.valueOf(passwordService.getTemporaryPassword().get(email)), new ArrayList<>());
    }
}
