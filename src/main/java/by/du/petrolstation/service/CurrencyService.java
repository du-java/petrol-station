package by.du.petrolstation.service;

import by.du.petrolstation.dto.RateDto;
import by.du.petrolstation.integration.CurrencyClient;
import by.du.petrolstation.integration.CurrencyResponse;
import by.du.petrolstation.properties.CurrencyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;
    private final CurrencyProperties currencyProperties;

    public List<RateDto> getCurrencyList() {
        return currencyProperties.getCurrencies().stream()
                .map(currencyClient::getRate)
                .map(this::buildRate)
                .collect(Collectors.toList());
    }

    private RateDto buildRate(CurrencyResponse resp) {
        return RateDto.builder()
                .rate(resp.getRate().toString())
                .currency(Currency.getInstance(resp.getCode().toUpperCase()).getDisplayName())
                .date(resp.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
