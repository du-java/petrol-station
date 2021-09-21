package by.du.petrolstation.service;

import by.du.petrolstation.dto.Dto;
import by.du.petrolstation.model.Model;

import java.util.List;

public interface RestService<D extends Dto, M extends Model> {
    D findById(Long id);

    List<D> findAll();

    D update(D dto);

    D create(D dto);

    void delete(Long id);
}
