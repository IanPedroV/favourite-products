package br.com.luizalabs.favourite.favouriteproducts.repository;

import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {

}
