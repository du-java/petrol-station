package by.du.petrolstation.service.impl;

import by.du.petrolstation.dto.StationDto;
import by.du.petrolstation.model.Station;
import by.du.petrolstation.repository.StationRepository;
import by.du.petrolstation.service.AbstractRestService;
import by.du.petrolstation.service.converter.StationConverter;
import org.springframework.stereotype.Service;

@Service
public class StationRestService extends AbstractRestService<StationDto, Station> {
    public StationRestService(StationRepository repository, StationConverter converter) {
        super(repository, converter);
    }
}
