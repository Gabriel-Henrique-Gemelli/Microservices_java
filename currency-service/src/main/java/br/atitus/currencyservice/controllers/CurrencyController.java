package br.atitus.currencyservice.controllers;

import br.atitus.currencyservice.entities.Currency;
import br.atitus.currencyservice.repositories.CurrencyRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyRepository currencyRepository;
    private final int serverPort;

    public CurrencyController(CurrencyRepository currencyRepository,
                              @Value("${server.port}") int serverPort) {
        this.currencyRepository = currencyRepository;
        this.serverPort = serverPort;
    }

    @GetMapping("/{value}/{source}/{target}")
    public ResponseEntity<Currency> convert(@PathVariable("value") BigDecimal value,
                                            @PathVariable("source") String source,
                                            @PathVariable("target") String target) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value must be a positive number.");
        }

        String normalizedSource = normalizeCurrencyCode(source, "source");
        String normalizedTarget = normalizeCurrencyCode(target, "target");

        Currency currency = currencyRepository
                .findBySourceCurrencyAndTargetCurrency(normalizedSource, normalizedTarget)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rate not found"));

        BigDecimal convertedValue = value
                .multiply(currency.getConversionRate())
                .setScale(2, RoundingMode.HALF_UP);

        currency.setConvertedValue(convertedValue);
        currency.setEnvironment("currency-service running on port " + serverPort);

        return ResponseEntity.ok(currency);
    }

    private String normalizeCurrencyCode(String code, String fieldName) {
        if (code == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, fieldName + " currency is required.");
        }
        String normalized = code.trim().toUpperCase(Locale.ROOT);
        if (!normalized.matches("[A-Z]{3}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    fieldName + " currency must be three alphabetic characters.");
        }
        return normalized;
    }
}
