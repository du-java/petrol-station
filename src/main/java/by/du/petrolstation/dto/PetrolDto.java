package by.du.petrolstation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetrolDto implements Dto {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 10, message = "Name's size is from 3 to 10")
    private String name;
    @NotNull
    @Positive(message = "Price should be positive")
    private BigDecimal price;
}
