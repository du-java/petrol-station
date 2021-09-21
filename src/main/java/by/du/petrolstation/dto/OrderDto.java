package by.du.petrolstation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Dto {
    @Positive
    @NotNull
    private Long id;
    @Positive
    @NotNull
    private BigDecimal amount;
    @Positive
    @NotNull
    private BigDecimal quantity;
    @NotBlank
    private String petrol;
    private BigDecimal price;
    private LocalDateTime date;
    private String user;
}
