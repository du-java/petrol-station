package by.du.petrolstation.integration;

import by.du.petrolstation.dto.RateDto;
import by.du.petrolstation.properties.CurrencyProperties;
import by.du.petrolstation.properties.PetrolCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Currency;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyClient {

    private final RestTemplate restTemplate;
    private final CurrencyProperties currencyProperties;
    private final Map<Currency, CurrencyResponse> currencyMap;

    public CurrencyResponse getRate(PetrolCurrency petrolCurrency) {

        final String url = UriComponentsBuilder
                .fromUriString(currencyProperties.getBaseUrl())
                .path(currencyProperties.getPath())
                .path(String.valueOf(petrolCurrency.getId()))
                .toUriString();

        final Currency currency = Currency.getInstance(petrolCurrency.getCurrency().toUpperCase());

        try {
            final ResponseEntity<CurrencyResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()), // new HttpEntity<>(body, httpHeaders),
                    CurrencyResponse.class
            );

            final CurrencyResponse currencyResponse = response.getBody();
            currencyMap.put(currency, currencyResponse);
            return currencyResponse;

        } catch (RestClientException ex) {
            log.error("{}", ex.getMessage());
            return currencyMap.getOrDefault(
                    currency,
                    new CurrencyResponse()
            );
        }
    }
}
