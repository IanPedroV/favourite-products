package br.com.luizalabs.favourite.favouriteproducts.service;

import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientUpdateFavouriteProductsForm;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.FavouriteProduct;
import br.com.luizalabs.favourite.favouriteproducts.model.Product;
import br.com.luizalabs.favourite.favouriteproducts.repository.ClientRepository;
import br.com.luizalabs.favourite.favouriteproducts.repository.FavouriteProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FavouriteProductRepository favouriteProductRepository;
    private final ProductService productService;

    public ClientService(ClientRepository clientRepository, FavouriteProductRepository favouriteProductRepository,
                         ProductService productService) {
        this.clientRepository = clientRepository;
        this.favouriteProductRepository = favouriteProductRepository;
        this.productService = productService;
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
        return clientRepository.findAll().stream().map(client ->
                new ClientDto(client, getProducts(client.getFavouriteProducts()))).collect(Collectors.toList());

    }

    public Set<Product> getProducts(Set<FavouriteProduct> favouriteProducts) {
        return favouriteProducts.stream().map(favouriteProduct ->
                productService.getProduct(favouriteProduct.getUuid())).collect(Collectors.toSet());
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
        favouriteProduct.setUuid(productId);
        favouriteProduct.setClientId(clientId);
        favouriteProductRepository.save(favouriteProduct);
        return favouriteProduct;
    }

    public boolean alreadyHaveProduct(ClientUpdateFavouriteProductsForm favouriteProductsForm, Client client) {
        return client.getFavouriteProducts().stream().anyMatch(favouriteProduct ->
                favouriteProduct.getUuid().equals(favouriteProductsForm.getFavouriteProductId()));
    }

    @Transactional
    public void delete(Long id) {
        Client client = this.clientRepository.getOne(id);
        this.clientRepository.delete(client);
    }

    @Transactional
    public Client findByEmail(String email) {
        return this.clientRepository.findByEmail(email);
    }
}
