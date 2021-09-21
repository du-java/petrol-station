package by.du.petrolstation.dto;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class RateDto {
    String currency;
    String rate;
    String date;
}
