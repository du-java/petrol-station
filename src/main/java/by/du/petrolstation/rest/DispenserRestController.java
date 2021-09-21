package by.du.petrolstation.rest;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.service.RestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dispenser")
public class DispenserRestController extends AbstractRestController<DispenserDto, Dispenser> {

    public DispenserRestController(RestService<DispenserDto, Dispenser> dispenserService) {
        super(dispenserService);
    }
}
