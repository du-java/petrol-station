package by.du.petrolstation.service;

import by.du.petrolstation.exception.NotFoundException;
import by.du.petrolstation.model.User;
import by.du.petrolstation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByName(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new NotFoundException(username));
    }
}
