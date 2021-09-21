package by.du.petrolstation.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class StationDto implements Dto {
    Long id;
    List<Long> dispensers;
    List<Long> tanks;
}
