package br.com.luizalabs.favourite.favouriteproducts.service;

import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientUpdateFavouriteProductsForm;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.FavouriteProduct;
import br.com.luizalabs.favourite.favouriteproducts.repository.ClientRepository;
import br.com.luizalabs.favourite.favouriteproducts.repository.FavouriteProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FavouriteProductRepository favouriteProductRepository;

    public ClientService(ClientRepository clientRepository, FavouriteProductRepository favouriteProductRepository) {
        this.clientRepository = clientRepository;
        this.favouriteProductRepository = favouriteProductRepository;
    }

    @Transactional
    public Client create(Client client) {
        return this.clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return this.clientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream().map(ClientDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Client update(Client newClient, Long id) {
        Client client = this.clientRepository.getOne(id);
        client.setEmail(newClient.getEmail());
        client.setName(newClient.getName());
        return client;
    }

    @Transactional
    public FavouriteProduct addFavouriteProduct(UUID productId, Long clientId) {
        FavouriteProduct favouriteProduct = new FavouriteProduct();
        favouriteProduct.setExternalId(productId);
        favouriteProduct.setClientId(clientId);
        favouriteProductRepository.save(favouriteProduct);
        return favouriteProduct;
    }

    public boolean alreadyHasProduct(ClientUpdateFavouriteProductsForm favouriteProductsForm, Client client) {
        return client.getFavouriteProducts().stream().noneMatch(favouriteProduct ->
                favouriteProduct.getExternalId().equals(favouriteProductsForm.getFavouriteProductId()));
    }

    @Transactional
    public void delete(Long id) {
        Client client = this.clientRepository.getOne(id);
        this.clientRepository.delete(client);
    }
}
