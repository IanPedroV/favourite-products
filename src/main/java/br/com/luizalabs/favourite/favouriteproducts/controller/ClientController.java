package br.com.luizalabs.favourite.favouriteproducts.controller;

import br.com.luizalabs.favourite.favouriteproducts.config.security.ClientDetailsService;
import br.com.luizalabs.favourite.favouriteproducts.config.security.JwtUtil;
import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.ClientUpdateFavouriteProductsForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.LoginForm;
import br.com.luizalabs.favourite.favouriteproducts.controller.form.RequestPasswordForm;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.model.JwtResponse;
import br.com.luizalabs.favourite.favouriteproducts.service.ClientService;
import br.com.luizalabs.favourite.favouriteproducts.service.EmailSenderService;
import br.com.luizalabs.favourite.favouriteproducts.service.ProductService;
import br.com.luizalabs.favourite.favouriteproducts.service.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ProductService productService;

    public ClientController(ClientService client, ProductService productService) {
        this.clientService = client;
        this.productService = productService;
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriBuilder) {
        Client client = clientService.create(clientForm.convert());
        URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientDto(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> read(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody @Valid ClientForm newClient) {
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            Client client = newClient.convert();
            clientService.update(client, id);
            return ResponseEntity.ok(new ClientDto(client));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/favouriteproduct")
    public ResponseEntity<ClientDto> addFavouriteProduct(@PathVariable Long id, @RequestBody @Valid ClientUpdateFavouriteProductsForm favouriteProductsForm) {
        if (productService.getProduct(favouriteProductsForm.getFavouriteProductId()) == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (clientService.alreadyHasProduct(favouriteProductsForm, client)) {
                clientService.addFavouriteProduct(favouriteProductsForm.getFavouriteProductId(), id);
                return ResponseEntity.noContent().build();
            } else return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}/favouriteproduct")
    public ResponseEntity<ClientDto> removeFavouriteProduct(@PathVariable Long id, @RequestBody @Valid ClientUpdateFavouriteProductsForm favouriteProductsForm) {
        //TODO: fill method
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.notFound().build();
    }

}
