package br.com.luizalabs.favourite.favouriteproducts.controller.form;

import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClientForm {
    @NotNull @NotEmpty @Length(min = 3)
    private String name;

    @NotNull @NotEmpty @Email
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client convert() {
        return new Client(this.name, this.email);
    }


}
