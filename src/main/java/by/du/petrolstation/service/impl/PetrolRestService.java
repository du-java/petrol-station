package by.du.petrolstation.service.impl;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.repository.PetrolRepository;
import by.du.petrolstation.service.AbstractRestService;
import by.du.petrolstation.service.converter.PetrolConverter;
import org.springframework.stereotype.Service;

@Service
public class PetrolRestService extends AbstractRestService<PetrolDto, Petrol> {
    public PetrolRestService(PetrolRepository repository, PetrolConverter converter) {
        super(repository, converter);
    }
}
