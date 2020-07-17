package br.com.luizalabs.favourite.favouriteproducts.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class FavouriteProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=16)
    private UUID uuid;
    private Long clientId;

    public FavouriteProduct() {
    }

    public FavouriteProduct(Long id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID externalId) {
        this.uuid = externalId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}

