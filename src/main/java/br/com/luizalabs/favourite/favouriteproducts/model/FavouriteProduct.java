package br.com.luizalabs.favourite.favouriteproducts.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class FavouriteProduct {
    @Id
    private UUID id;
    private Long clientId;

    public FavouriteProduct() {
    }

    public FavouriteProduct(UUID id, Long clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}

