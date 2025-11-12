package br.edu.atitus.Cart_service.service;

import org.springframework.stereotype.Service;

import br.edu.atitus.Cart_service.Entity.cartEntity;
import br.edu.atitus.Cart_service.repository.cartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class cartService {

	private final cartRepository repository;
	
	public cartEntity existByUserIdAndCreate(Long userId) {
		if(repository.existsByUserId(userId) == true) {
			throw new IllegalStateException("Carrinho ja existe para usuario");
		}
		cartEntity cart = new cartEntity();
		cart.setUserId(userId);
		return repository.save(cart);
	}
	
	public cartEntity findByUserId(Long userId) {
		cartEntity cart = repository.findByUserId(userId).orElse(existByUserIdAndCreate(userId));
		return cart;
	}
	
}
