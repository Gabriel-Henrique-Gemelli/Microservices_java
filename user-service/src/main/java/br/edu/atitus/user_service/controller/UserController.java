package br.edu.atitus.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.atitus.user_service.entity.userEntity;
import br.edu.atitus.user_service.service.userService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final userService userService;
	
	public record ApiError(String code, String message){}

	@PostMapping("/save")
	public ResponseEntity<?> salvar(@RequestBody userEntity user){
		try {
			userEntity entidade =  userService.salvar(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(entidade);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiError("USER_CONFLICT", "Dados em conflito (ex.: email já usado)."));
		}
	}
}
