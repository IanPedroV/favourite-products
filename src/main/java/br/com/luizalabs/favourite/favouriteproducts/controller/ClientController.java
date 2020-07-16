package br.com.luizalabs.favourite.favouriteproducts.controller;

import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.service.ClientService;
import org.springframework.http.ResponseEntity;
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

    public ClientController(ClientService client) {
        this.clientService = client;
    }


    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientDto> create(@RequestBody @Valid Client client, UriComponentsBuilder uriBuilder) {
        clientService.create(client);
        URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientDto(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> read(@PathVariable Integer id) {
        Optional<Client> client = clientService.findBy(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDto> update(@PathVariable Integer id, @RequestBody @Valid Client newClient) {
        Optional<Client> optionalClient = clientService.findBy(id);
        if (optionalClient.isPresent())
          clientService.update(newClient,id);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.notFound().build();
    }

}