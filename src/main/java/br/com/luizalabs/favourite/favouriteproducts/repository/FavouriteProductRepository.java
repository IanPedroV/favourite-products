package br.com.luizalabs.favourite.favouriteproducts.repository;

import br.com.luizalabs.favourite.favouriteproducts.model.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Column;
import java.util.UUID;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {

    FavouriteProduct findByUuid(UUID uuid);

}
