package by.du.petrolstation.service;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.repository.DispenserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DispenserService {

    private final DispenserRepository dispenserRepository;
    private final StationService stationService;
    private final DispenserDispenserDtoConverter dispenserDispenserDtoConverter;
    private final DispenserDtoDispenserConverter dispenserDtoDispenserConverter;

    public List<DispenserDto> findAll() {
        return dispenserRepository.findAll().stream()
                .map(dispenserDispenserDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public Dispenser findById(Long id) {
        return dispenserRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void update(DispenserDto dto) {
        final Dispenser dispenser = dispenserDtoDispenserConverter.convert(dto);
        dispenserRepository.save(dispenser);
    }

    public void deleteById(Long id) {
        stationService.deleteByDispenser(findById(id));
        dispenserRepository.deleteById(id);
    }

    public void deleteByPetrol(Petrol petrol) {
        final List<Dispenser> dispensers = dispenserRepository.findAllByPetrol(petrol).stream()
                .peek(d -> d.getPetrolList().remove(petrol))
                .collect(Collectors.toList());

        dispenserRepository.saveAll(dispensers);
    }

    public void add(DispenserDto dispenserDto) {
        final Dispenser saved = dispenserRepository.save(dispenserDtoDispenserConverter.convert(dispenserDto));
        stationService.addDispenser(saved);
    }

    public DispenserDto findByIdDto(Long id) {
        return dispenserDispenserDtoConverter.convert(findById(id));
    }
}
