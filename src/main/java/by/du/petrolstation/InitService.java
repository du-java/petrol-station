package by.du.petrolstation;

import by.du.petrolstation.model.*;
import by.du.petrolstation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("init")
@RequiredArgsConstructor
public class InitService {

    private final TankRepository tankRepository;
    private final StationRepository stationRepository;
    private final PetrolRepository petrolRepository;
    private final DispenserRepository dispenserRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @PostConstruct
    public void init() {
        final User admin = User.builder()
                .name("admin")
                .password(encoder.encode("1234"))
                .roles(List.of(Role.ADMIN, Role.MANAGER))
                .build();
        userRepository.save(admin);
        final Petrol ai92 = Petrol.builder()
                .name("AI92")
                .price(new BigDecimal("1.98"))
                .build();
        final Petrol ai95 = Petrol.builder()
                .name("AI95")
                .price(new BigDecimal("2.01"))
                .build();
        final Petrol diesel = Petrol.builder()
                .name("Diesel")
                .price(new BigDecimal("2.01"))
                .build();

        final List<Petrol> petrols = List.of(ai92, ai95, diesel);

        petrolRepository.saveAll(petrols);

        final Dispenser dispenser1 = Dispenser.builder()
                .petrolList(petrols)
                .build();
        final Dispenser dispenser2 = Dispenser.builder()
                .petrolList(petrols)
                .build();
        final Dispenser dispenser3 = Dispenser.builder()
                .petrolList(List.of(diesel))
                .build();

        final List<Dispenser> dispensers = Arrays.asList(dispenser1, dispenser2, dispenser3);


    dispenserRepository.saveAll(dispensers);

        final Tank tankAi92 = Tank.builder()
                .petrol(ai92)
                .quantity(new BigDecimal("1000"))
                .build();
        final Tank tankAi95 = Tank.builder()
                .petrol(ai95)
                .quantity(new BigDecimal("1000"))
                .build();
        final Tank tankDiesel = Tank.builder()
                .petrol(diesel)
                .quantity(new BigDecimal("1000"))
                .build();

        final List<Tank> tanks = List.of(tankAi92, tankAi95, tankDiesel);

        tankRepository.saveAll(tanks);

        final Station station = Station.builder()
                .dispensers(dispensers)
                .tanks(tanks)
                .build();

        final Station savedStation = stationRepository.save(station);

//        final User admin = User.builder()
//                .name("admin")
//                .roles(List.of(Role.ADMIN, Role.MANAGER))
//                .build();
//        final User manager = User.builder()
//                .name("manager")
//                .roles(List.of(Role.MANAGER))
//                .build();
//        final User client = User.builder()
//                .name("client")
//                .roles(List.of(Role.CLIENT))
//                .build();
//
//        userRepository.saveAll(List.of(admin, manager, client));
//
//        final User sa = User.builder()
//                .name("sa")
//                .roles(List.of(Role.SUPER_ADMIN))
//                .build();
//        userRepository.save(sa);
    }
}
