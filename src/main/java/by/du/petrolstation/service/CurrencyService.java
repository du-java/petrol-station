package by.du.petrolstation.service;

import by.du.petrolstation.integration.CurrencyClient;
import by.du.petrolstation.model.PetrolCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;

    public List<PetrolCurrency> getCurrencyList() {
        return currencyClient.getCurrencyList();
    }
}
