package by.du.petrolstation.facade;

import by.du.petrolstation.model.Role;
import by.du.petrolstation.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.du.petrolstation.model.Role.CLIENT;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final OrderFacade orderFacade;

    public boolean isClient(Authentication authentication) {
        User user = orderFacade.getUser(authentication);
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (CLIENT.equals(role)) {
                return true;
            }
        }
        return false;
    }
}
