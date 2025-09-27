package br.atitus.currencyservice.repositories;

import br.atitus.currencyservice.entities.Currency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
}
