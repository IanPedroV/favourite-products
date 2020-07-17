package br.com.luizalabs.favourite.favouriteproducts.service;

import br.com.luizalabs.favourite.favouriteproducts.model.FavouriteProduct;
import br.com.luizalabs.favourite.favouriteproducts.repository.ClientRepository;
import br.com.luizalabs.favourite.favouriteproducts.repository.FavouriteProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FavouriteProductService {
    private final FavouriteProductRepository favouriteProductRepository;
    private final ClientRepository clientRepository;

    public FavouriteProductService(FavouriteProductRepository favouriteProductRepository,
                                   ClientRepository clientRepository) {
        this.favouriteProductRepository = favouriteProductRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void deleteByExternalId(UUID externalId) {
        FavouriteProduct favouriteProduct = this.favouriteProductRepository.findByUuid(externalId);
        if (clientRepository.findById(favouriteProduct.getClientId()).isPresent())
            clientRepository.findById(favouriteProduct.getClientId()).get().removeFavourite(favouriteProduct);

    }
}
