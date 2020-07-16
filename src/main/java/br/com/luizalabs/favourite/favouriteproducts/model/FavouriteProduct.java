package br.com.luizalabs.favourite.favouriteproducts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class FavouriteProduct {
    @Id
    @GeneratedValue
    private UUID id;
    private int clientId;

    public FavouriteProduct() {
    }

    public FavouriteProduct(UUID id, int clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
