package by.du.petrolstation.service;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DispenserDispenserDtoConverter implements Converter<Dispenser, DispenserDto> {
    @Override
    public DispenserDto convert(Dispenser dispenser) {
        return DispenserDto.builder()
                .id(dispenser.getId())
                .petrols(dispenser.getPetrolList().stream().map(Petrol::getName).collect(Collectors.toList()))
                .build();
    }
}
