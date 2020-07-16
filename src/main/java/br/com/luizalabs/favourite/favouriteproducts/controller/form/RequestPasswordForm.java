package br.com.luizalabs.favourite.favouriteproducts.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestPasswordForm {
    @NotNull
    @NotEmpty
    @Email
    private String email;


    public String getEmail() {
        return email;
    }

}
