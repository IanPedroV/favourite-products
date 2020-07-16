package br.com.luizalabs.favourite.favouriteproducts.controller.dto;

import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.FavouriteProduct;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClientDto {
    private Long id;
    private String name;
    private String email;
    private Set<FavouriteProduct> favouriteFavouriteProducts = new LinkedHashSet<>();

    public ClientDto(Long id, String name, String email, Set<FavouriteProduct> favouriteFavouriteProducts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.favouriteFavouriteProducts = favouriteFavouriteProducts;
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.favouriteFavouriteProducts = client.getFavouriteProducts();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public Set<FavouriteProduct> getFavouriteFavouriteProducts() {
        return Collections.unmodifiableSet(favouriteFavouriteProducts);
    }

    public void setFavouriteFavouriteProducts(Set<FavouriteProduct> favouriteFavouriteProducts) {
        this.favouriteFavouriteProducts = favouriteFavouriteProducts;
    }
}
