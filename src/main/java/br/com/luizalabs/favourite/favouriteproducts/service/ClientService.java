package br.com.luizalabs.favourite.favouriteproducts.service;

import br.com.luizalabs.favourite.favouriteproducts.controller.dto.ClientDto;
import br.com.luizalabs.favourite.favouriteproducts.model.Client;
import br.com.luizalabs.favourite.favouriteproducts.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {


    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client create(Client client) {
        return this.clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Optional<Client> findBy(Long id) {
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
    public void delete(Long id) {
        Client client = this.clientRepository.getOne(id);
        this.clientRepository.delete(client);
    }
}
