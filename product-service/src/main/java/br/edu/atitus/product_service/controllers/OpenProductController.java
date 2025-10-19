package br.edu.atitus.product_service.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.product_service.clients.CurrencyClient;
import br.edu.atitus.product_service.clients.CurrencyResponse;
import br.edu.atitus.product_service.entities.ProductEntity;
import br.edu.atitus.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class OpenProductController {

	 private final ProductRepository repository;
	    private final CurrencyClient client;
	    private final CacheManager cacheManager;

	    @Value("${server.port}")
	    private int serverPort;

	    @GetMapping("/{idProduct}/{targetCurrency}")
	    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long idProduct, @PathVariable String targetCurrency) {

	        String nameCache = "Product";
	        String normalizedTarget = targetCurrency == null ? "" : targetCurrency.toUpperCase(Locale.ROOT);

	        ProductEntity product = repository.findById(idProduct)
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        String productCurrency = product.getCurrency() == null ? "" : product.getCurrency().toUpperCase(Locale.ROOT);

	        product.setEnvironment("Product service port: " + serverPort);

	        if (productCurrency.equals(normalizedTarget)) {
	            product.setConvertedPrice(product.getPrice());
	        } else {
	            CurrencyResponse currency = client.getCurrency(product.getPrice(), productCurrency, normalizedTarget);
	            product.setConvertedPrice(currency.getConvertedValue());
	            product.setEnvironment(product.getEnvironment() + " - " + currency.getEnvironment());
	        }

	        String cacheKey = idProduct + ":" + productCurrency + "->" + normalizedTarget;
	        Cache cache = cacheManager.getCache(nameCache);
	        if (cache != null) {
	            cache.put(cacheKey, product);
	        }

	        return ResponseEntity.ok(product);
	    }
}
