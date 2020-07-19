package br.com.luizalabs.favourite.favouriteproducts.model;


import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;

import javax.persistence.*;
import java.util.*;

@Entity
public class Client {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clientId")
    Set<FavouriteProduct> favouriteProducts = new LinkedHashSet<>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if (obj == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id == null)
            return other.id == null;
        else return id.equals(other.id);
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public Client() {

    }

    public Client(ClientDto clientDto) {
        this.name = clientDto.getName();
        this.email = clientDto.getEmail();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<FavouriteProduct> getFavouriteProducts() {
        return Collections.unmodifiableSet(favouriteProducts);
    }

   public void addFavourite(FavouriteProduct favouriteProduct) {
        this.favouriteProducts.add(favouriteProduct);
   }

   public void removeFavourite(FavouriteProduct favouriteProduct) {
        this.favouriteProducts.remove(favouriteProduct);
   }
}
