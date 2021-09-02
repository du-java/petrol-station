package by.du.petrolstation.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TankDto {
    private Long id;
    private String petrol;
    private BigDecimal quantity;
}
