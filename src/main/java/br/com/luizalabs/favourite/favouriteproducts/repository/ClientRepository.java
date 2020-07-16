package br.com.luizalabs.favourite.favouriteproducts.repository;

import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
