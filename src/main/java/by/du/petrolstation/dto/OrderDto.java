package by.du.petrolstation.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
public class OrderDto {

    private Long id;
    private BigDecimal amount;
    private BigDecimal quantity;
    private String petrol;
    private BigDecimal price;
    private LocalDateTime date;

}
