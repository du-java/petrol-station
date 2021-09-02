package by.du.petrolstation.service;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.repository.PetrolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PetrolService {

    private final PetrolRepository petrolRepository;

    public List<PetrolDto> findAll() {
        return petrolRepository.findAll().stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    public Petrol findById(Long id) {
        return petrolRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public PetrolDto findByIdDto(Long id) {
        return converter(findById(id));
    }

    public Petrol update(Petrol petrol) {
        return petrolRepository.save(petrol);
    }


    public void add(PetrolDto petrolDto) {
        final Petrol petrol = Petrol.builder()
                .price(petrolDto.getPrice())
                .name(petrolDto.getName())
                .build();
        petrolRepository.save(petrol);
    }

    public Petrol findByName(String petrolName) {
        return petrolRepository.findByName(petrolName)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void deleteById(Long id) {
        petrolRepository.deleteById(id);
    }

    private PetrolDto converter(Petrol petrol) {
        return PetrolDto.builder()
                .id(petrol.getId())
                .name(petrol.getName())
                .price(petrol.getPrice())
                .build();
    }
}
