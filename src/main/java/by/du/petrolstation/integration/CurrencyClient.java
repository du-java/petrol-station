package by.du.petrolstation.integration;

import by.du.petrolstation.model.PetrolCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyClient {

    private final RestTemplate restTemplate;

    public List<PetrolCurrency> getCurrencyList() {
        return Collections.emptyList();
    }

}
