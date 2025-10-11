package br.edu.atitus.currency_service.controllers;

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
	
	@GetMapping("/{amount}/{source}/{target}")
	public ResponseEntity<CurrencyEntity> getCurrency(
			@PathVariable double amount,
			@PathVariable String source,
			@PathVariable String target) {
		
		CurrencyEntity entity = repository.findBySourceAndTarget(source, target)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency not found"));
		
		entity.setConvertedValue(amount * entity.getConversionRate());
		entity.setEnviroment(System.getenv("PORT"));
		
		return ResponseEntity.ok(entity);
	}
	
	
}
