package by.du.petrolstation.service;

import by.du.petrolstation.dto.SingUpUserDto;
import by.du.petrolstation.model.Role;
import by.du.petrolstation.model.User;
import by.du.petrolstation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SingUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(SingUpUserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmation())) {
            throw new IllegalArgumentException("password not the same");
        }
        if (userRepository.findByName(userDto.getName()).isPresent()) {
            throw new IllegalArgumentException("user already exists");
        }
        User user = User.builder()
                .name(userDto.getName())
                .roles(List.of(Role.CLIENT))
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(user);
    }
}
