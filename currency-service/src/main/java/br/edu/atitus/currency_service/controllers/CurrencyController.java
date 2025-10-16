package br.edu.atitus.currency_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.atitus.currency_service.entities.CurrencyEntity;
import br.edu.atitus.currency_service.repositories.CurrencyRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

	private final CurrencyRepository repository;


	@Value("${server.port}")
	private int serverPort;

	@GetMapping("/{value}/{source}/{target}")
	public ResponseEntity<CurrencyEntity> getCurrency(
			@PathVariable double value,
			@PathVariable String source,
			@PathVariable String target) throws Exception {
		
		String from = source.toUpperCase();
		String to = target.toUpperCase();
		
		CurrencyEntity entity = repository.findBySourceAndTarget(from,to).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Deu ruim ao fazer essa porra"));

		entity.setConvertedValue(value * entity.getConversionRate());
		entity.setEnviroment("Currency-service running on port:" + serverPort);
		
		
		return ResponseEntity.ok(entity);
	}
	
	
}
