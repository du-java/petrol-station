package by.du.petrolstation.service.impl;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.repository.TankRepository;
import by.du.petrolstation.service.AbstractRestService;
import by.du.petrolstation.service.converter.TankConverter;
import org.springframework.stereotype.Service;

@Service
public class TankRestService extends AbstractRestService<TankDto, Tank> {
    public TankRestService(TankRepository repository, TankConverter converter) {
        super(repository, converter);
    }
}
