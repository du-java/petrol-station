package by.du.petrolstation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingUpUserDto {
    @NotBlank
    @Size(min=2, max=30,message = "text")
    private String name;
    @NotBlank
    @Size(min=2, max=30)
    private String password;
    @NotBlank
    @Size(min=2, max=30)
    private String confirmation;
}
