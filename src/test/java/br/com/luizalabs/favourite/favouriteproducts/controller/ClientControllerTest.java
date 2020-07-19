package br.com.luizalabs.favourite.favouriteproducts.controller;


import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientUpdateFavouriteProductsForm;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.Product;
import br.com.luizalabs.favourite.favouriteproducts.service.ClientService;
import br.com.luizalabs.favourite.favouriteproducts.service.FavouriteProductService;
import br.com.luizalabs.favourite.favouriteproducts.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;
    @Mock
    private ClientService clientService;
    @Mock
    private ProductService productService;
    @Mock
    private FavouriteProductService favouriteProductService;
    @Mock
    UriComponentsBuilder uriBuilder;
    private List<ClientDto> clientList;

    @BeforeEach
    void setUp() {
        this.clientList = new ArrayList<>();
        this.clientList.add(new ClientDto(new Client("user1@gmail.com", "pwd1")));
        this.clientList.add(new ClientDto(new Client("user2@gmail.com", "pwd2")));
        this.clientList.add(new ClientDto(new Client("user3@gmail.com", "pwd3")));
    }

    @Test
    void shouldFetchAllClients() {
        Mockito.when(clientService.getAll()).thenReturn(clientList);
        Assertions.assertThat(clientList.size()).isEqualTo(clientController.getAll().size());
    }

    @Test
    void shouldCreateClient() {
        Mockito.when(clientService.create(ArgumentMatchers.any())).thenReturn(new Client(clientList.get(0)));
        Mockito.when(uriBuilder.path(ArgumentMatchers.anyString())).thenReturn(UriComponentsBuilder.newInstance());
        ResponseEntity<ClientDto> response = clientController.create(new ClientForm(), uriBuilder);
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    void shouldAddFavouriteProduct() {
        Mockito.when(clientService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Client(clientList.get(0))));
        Mockito.when(productService.getProduct(ArgumentMatchers.any())).thenReturn(new Product());
        ResponseEntity<ClientDto> response = clientController.addFavouriteProduct( 1L, new ClientUpdateFavouriteProductsForm());
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    @Test
    void shouldRemoveFavouriteProduct() {
        Mockito.when(clientService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Client(clientList.get(0))));
        Mockito.when(clientService.alreadyHaveProduct(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(true);
        ResponseEntity<ClientDto> response = clientController.removeFavouriteProduct(1L, new ClientUpdateFavouriteProductsForm());
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    @Test
    void shouldDelete() {
        Mockito.when(clientService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Client(clientList.get(0))));
        ResponseEntity<?> response = clientController.delete(1L);
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    @Test
    void shouldUpdate() {
        Mockito.when(clientService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Client(clientList.get(0))));
        ResponseEntity<?> response = clientController.update(1L, new ClientForm());
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
    }


}

