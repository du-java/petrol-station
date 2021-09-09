package by.du.petrolstation.config;

import by.du.petrolstation.integration.CurrencyResponse;
import by.du.petrolstation.properties.CurrencyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Currency;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({
        CurrencyProperties.class
})
public class IntegrationConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    @Bean("currencyMap")
    public Map<Currency, CurrencyResponse> currencyMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }
}
