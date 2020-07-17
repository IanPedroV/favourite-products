package br.com.luizalabs.favourite.favouriteproducts.controller.dto;

import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.Product;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {
    private Long id;
    private String name;
    private String email;
    private Set<Product> favouriteProducts = new LinkedHashSet<>();

    public ClientDto(Client client, Set<Product> products) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.favouriteProducts = products;
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


    public Set<Product> getFavouriteProducts() {
        return Collections.unmodifiableSet(favouriteProducts);
    }

    public void setFavouriteProducts(Set<Product> favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
    }
}
