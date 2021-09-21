package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.UserDto;
import by.du.petrolstation.model.User;
import by.du.petrolstation.service.PetrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public UserDto convert(User model) {
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}
