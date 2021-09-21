package by.du.petrolstation.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserDto implements Dto {
    Long id;
    String name;
    List<String> roles;
}
