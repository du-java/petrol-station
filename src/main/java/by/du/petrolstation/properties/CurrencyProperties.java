package by.du.petrolstation.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties("integration")
public class CurrencyProperties {
    private String baseUrl;
    private String path;
    private List<PetrolCurrency> currencies;
}
