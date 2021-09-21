package by.du.petrolstation.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TankDto implements Dto {
    private Long id;
    @NotBlank(message = "Petrol is mandatory")
    private String petrol;
    @Positive(message = "Quantity should be positive")
    private BigDecimal quantity;
}
