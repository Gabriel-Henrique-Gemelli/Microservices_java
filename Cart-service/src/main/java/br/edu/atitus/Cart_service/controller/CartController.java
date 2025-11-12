package br.edu.atitus.Cart_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.Cart_service.DTO.ProductResponse;
import br.edu.atitus.Cart_service.Entity.CartItens;
import br.edu.atitus.Cart_service.Entity.cartEntity;
import br.edu.atitus.Cart_service.client.ProductClient;
import br.edu.atitus.Cart_service.repository.cartRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	private final cartRepository repository;
	private final ProductClient client;
	
	
	@GetMapping("/{idCart}")
	public ResponseEntity<cartEntity> getCart(@PathVariable Long idCart) {
		cartEntity cart = repository.findById(idCart).orElse(null);
		return ResponseEntity.ok(cart);
	}
	
	@PostMapping("/adicionar/{idProduto}")
	public ResponseEntity<CartItens> addItemToCart(@PathVariable Long idProduto) {
		ProductResponse item = client.getProduct(idProduto);
		CartItens cartItem = new CartItens();
		cartItem.setCart(item.getId());
	}
}
