package by.du.petrolstation.rest;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.service.RestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tank")
public class TankRestController extends AbstractRestController<TankDto, Tank> {

    public TankRestController(RestService<TankDto, Tank> tankService) {
        super(tankService);
    }
}
