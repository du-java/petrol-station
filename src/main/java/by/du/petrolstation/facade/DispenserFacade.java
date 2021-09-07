package by.du.petrolstation.facade;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.service.DispenserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispenserFacade {

    private final DispenserService dispenserService;

    public List<DispenserDto> findAll() {
        return dispenserService.findAll();
    }

    public void add(DispenserDto dispenserDto) {
        dispenserService.add(dispenserDto);
    }

    public DispenserDto findById(Long id) {
        return dispenserService.findByIdDto(id);
    }

    public void update(DispenserDto dispenserDto) {
        dispenserService.update(dispenserDto);
    }

    public void deleteById(Long id) {
        dispenserService.deleteById(id);
    }
}
