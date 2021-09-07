package by.du.petrolstation.service;

import by.du.petrolstation.model.Dispenser;
import by.du.petrolstation.model.Station;
import by.du.petrolstation.model.Tank;
import by.du.petrolstation.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    public Station findById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Station update(Station station) {
        return stationRepository.save(station);
    }

    public void deleteById(Long id) {
        stationRepository.deleteById(id);
    }

    public void deleteTanks(List<Tank> tanks) {
        final Station station = find();
        station.getTanks().removeAll(tanks);
        stationRepository.save(station);
    }

    public Station find() {
        return stationRepository.findAll().stream().findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public void addTank(Tank tank) {
        final Station station = find();
        station.getTanks().add(tank);
        stationRepository.save(station);
    }

    public void addDispenser(Dispenser dispenser) {
        final Station station = find();
        station.getDispensers().add(dispenser);
        stationRepository.save(station);
    }

    public void deleteByDispenser(Dispenser dispenser) {
        Station station = find();
        station.getDispensers().remove(dispenser);
        stationRepository.save(station);
    }
}
