package by.du.petrolstation.service.impl;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.repository.DispenserRepository;
import by.du.petrolstation.service.AbstractRestService;
import by.du.petrolstation.service.converter.DispenserConverter;
import org.springframework.stereotype.Service;

@Service
public class DispenserRestService extends AbstractRestService<DispenserDto, Dispenser> {
    public DispenserRestService(DispenserRepository repository, DispenserConverter converter) {
        super(repository, converter);
    }
}
