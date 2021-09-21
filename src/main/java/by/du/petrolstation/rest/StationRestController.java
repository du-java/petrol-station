package by.du.petrolstation.rest;

import by.du.petrolstation.dto.StationDto;
import by.du.petrolstation.model.Station;
import by.du.petrolstation.service.RestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/station")
public class StationRestController extends AbstractRestController<StationDto, Station> {

    public StationRestController(RestService<StationDto, Station> stationService) {
        super(stationService);
    }
}
