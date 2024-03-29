package br.com.luizalabs.favourite.favouriteproducts.service;

import br.com.luizalabs.favourite.favouriteproducts.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final Map<UUID, Product> cachedProducts = new HashMap<>();

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Product getProduct(UUID id) {
        String url = "http://challenge-api.luizalabs.com/api/product/" + id.toString() + "/?";
        if (cachedProducts.containsKey(id))
            return cachedProducts.get(id);
        Product product;
        try {
            product = this.restTemplate.getForObject(url, Product.class);
        } catch (HttpClientErrorException e) {
            return null;
        }
        cachedProducts.put(id, product);
        return product;
    }


}