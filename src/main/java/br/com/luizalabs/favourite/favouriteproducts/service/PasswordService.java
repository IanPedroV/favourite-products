package br.com.luizalabs.favourite.favouriteproducts.service;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class PasswordService {
    private Map<String, Integer> temporaryPassword = new HashMap<>();

    public PasswordService() {
    }

    public void add(String email, Integer password) {
        this.temporaryPassword.put(email, password);
    }


    public void remove(String email) {
        this.temporaryPassword.remove(email);
    }

    public int generatePassword() {
        return 100000 + (int) (Math.random() * ((999999 - 100000) + 1));
    }

    public Map<String, Integer> getTemporaryPassword() {
        return Collections.unmodifiableMap(temporaryPassword);
    }
}
