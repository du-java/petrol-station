package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.service.PetrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TankConverter implements Converter<TankDto, Tank> {

    private final PetrolService petrolService;

    @Override
    public Tank convert(TankDto dto) {
        return Tank.builder()
                .id(dto.getId())
                .petrol(petrolService.findByName(dto.getPetrol()))
                .quantity(dto.getQuantity())
                .build();
    }

    @Override
    public TankDto convert(Tank model) {
        return TankDto.builder()
                .id(model.getId())
                .petrol(model.getPetrol().getName())
                .quantity(model.getQuantity())
                .build();
    }
}
