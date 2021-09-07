package by.du.petrolstation.model;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class PetrolCurrency {
    private Currency currency;
    private BigDecimal rate;
}
