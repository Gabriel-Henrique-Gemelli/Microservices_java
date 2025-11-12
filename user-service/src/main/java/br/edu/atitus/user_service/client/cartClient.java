package br.edu.atitus.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.atitus.user_service.dto.CartDTO;
import br.edu.atitus.user_service.dto.CreateCartRequest;

@FeignClient(name = "cart-service")
public interface cartClient {

    @PostMapping("/carts")
    CartDTO create(@RequestBody CreateCartRequest req);

    @GetMapping("/carts/by-user/{userId}")
    CartDTO findByUser(@PathVariable Long userId);
}
