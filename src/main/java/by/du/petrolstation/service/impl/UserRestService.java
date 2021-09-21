package by.du.petrolstation.service.impl;

import by.du.petrolstation.dto.UserDto;
import by.du.petrolstation.model.User;
import by.du.petrolstation.repository.UserRepository;
import by.du.petrolstation.service.AbstractRestService;
import by.du.petrolstation.service.converter.UserConverter;
import org.springframework.stereotype.Service;

@Service
public class UserRestService extends AbstractRestService<UserDto, User> {
    public UserRestService(UserRepository repository, UserConverter converter) {
        super(repository, converter);
    }
}
