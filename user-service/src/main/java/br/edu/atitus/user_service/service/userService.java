package br.edu.atitus.user_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.atitus.user_service.client.cartClient;
import br.edu.atitus.user_service.dto.CreateCartRequest;
import br.edu.atitus.user_service.entity.userEntity;
import br.edu.atitus.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userService {
	
	private final UserRepository repository;
	private final PasswordEncoder Encoder;
	private final cartClient client;
	
	public userEntity salvar(userEntity user) {
		
		user.setSenha(Encoder.encode(user.getSenha()));
		userEntity salvado = repository.save(user);
		client.create(new CreateCartRequest(salvado.getId()));
		return salvado;
	}
}
