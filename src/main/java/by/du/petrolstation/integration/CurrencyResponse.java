package by.du.petrolstation.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CurrencyResponse {
    @JsonProperty("Cur_ID")
    private Long id;
    @JsonProperty("Cur_Abbreviation")
    private String code;
    @JsonProperty("Cur_OfficialRate")
    private BigDecimal rate;
    @JsonProperty("Date")
    private LocalDateTime date;
}
