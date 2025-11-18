package br.edu.atitus.Cart_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.Cart_service.Entity.cartEntity;
import br.edu.atitus.Cart_service.service.cartService;
import lombok.RequiredArgsConstructor;

record CreateCartRequest(Long userId) {}

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	private final cartService service;
//	private final ProductClient client;
	
	
	@PostMapping("/create")
    public cartEntity create(@RequestBody CreateCartRequest req) {
        return service.existByUserIdAndCreate(req.userId());
    }

    @GetMapping("/by-user/{userId}")
    public cartEntity byUser(@PathVariable Long userId) {
        return service.existByUserIdAndCreate(userId);
    }
    
    @PostMapping("/additem/{idUser}/{ProductId}/{currency}")
    public cartEntity AddItem(
    		@PathVariable Long idUser,
    		@PathVariable Long ProductId,
    		@PathVariable String currency){
    	
    	
        return service.addItem(idUser,ProductId,currency);
    }
}
