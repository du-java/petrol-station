package by.du.petrolstation.rest;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.service.RestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/petrol")
public class PetrolRestController extends AbstractRestController<PetrolDto, Petrol> {

    public PetrolRestController(RestService<PetrolDto, Petrol> petrolService) {
        super(petrolService);
    }
}
