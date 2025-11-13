package br.edu.atitus.Cart_service.service;

import org.springframework.stereotype.Service;

import br.edu.atitus.Cart_service.Entity.cartEntity;
import br.edu.atitus.Cart_service.client.ProductClient;
import br.edu.atitus.Cart_service.repository.CartItemRepository;
import br.edu.atitus.Cart_service.repository.cartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class cartService {

	private final cartRepository repository;
	private final CartItemRepository itemRepository;
	private final ProductClient client;
	
	
    @Transactional
    public cartEntity existByUserIdAndCreate(Long userId) {
        return repository.findByUserId(userId).orElseGet(() -> {
            cartEntity cart = new cartEntity();
            cart.setUserId(userId);
            return repository.save(cart);
        });
    }
	
	public cartEntity findByUserId(Long userId) {
		cartEntity cart = repository.findByUserId(userId).orElse(existByUserIdAndCreate(userId));
		return cart;
	}
	
	public cartEntity addItem(Long cartId, Long productId, String productName, Double productPrice, Integer quantity) {
		
	}
	
}
