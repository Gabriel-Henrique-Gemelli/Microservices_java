package br.edu.atitus.product_service.controllers;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${server.port}")
	private int serverPort;

	@GetMapping("/{idProduct}/{targetCurrency}")
	public ResponseEntity<ProductEntity> getProduct(@PathVariable Long idProduct, @PathVariable String targetCurrency) {

		ProductEntity product = repository.findById(idProduct)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		if(product.getCurrency().equals(targetCurrency)) {
			product.setConvertedPrice(product.getPrice());
			
		}else {
			CurrencyResponse currency = client.getCurrency(product.getPrice(),product.getCurrency(), targetCurrency);
			product.setConvertedPrice(currency.getConvertedValue());
			product.setEnvironment(product.getEnvironment() + " | " + currency.getEnvironment());
			product.setConvertedPrice(product.getPrice());
		}
		
		product.setEnvironment("Product service port: " + serverPort);
		
		 
		
		return ResponseEntity.ok(product);

	}
}
