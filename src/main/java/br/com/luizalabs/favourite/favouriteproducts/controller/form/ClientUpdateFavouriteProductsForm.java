package br.com.luizalabs.favourite.favouriteproducts.controller.form;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ClientUpdateFavouriteProductsForm {
    @NotNull
    private UUID favouriteProductId;

    public ClientUpdateFavouriteProductsForm(UUID favouriteProductId, Long clientId) {
        this.favouriteProductId = favouriteProductId;
    }

    public ClientUpdateFavouriteProductsForm() {
    }

    public UUID getFavouriteProductId() {
        return favouriteProductId;
    }

}
