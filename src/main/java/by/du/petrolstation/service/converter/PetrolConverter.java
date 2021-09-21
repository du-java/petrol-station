package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.service.PetrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetrolConverter implements Converter<PetrolDto, Petrol> {

    @Override
    public Petrol convert(PetrolDto dto) {
        return Petrol.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }

    @Override
    public PetrolDto convert(Petrol model) {
        return PetrolDto.builder()
                .id(model.getId())
                .name(model.getName())
                .price(model.getPrice())
                .build();
    }
}
