package by.du.petrolstation.rest;

import by.du.petrolstation.dto.UserDto;
import by.du.petrolstation.model.User;
import by.du.petrolstation.service.RestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController extends AbstractRestController<UserDto, User> {

    public UserRestController(RestService<UserDto, User> userService) {
        super(userService);
    }
}
