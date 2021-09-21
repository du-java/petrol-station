package by.du.petrolstation.service.converter;

import by.du.petrolstation.dto.Dto;
import by.du.petrolstation.model.Model;

public interface Converter<D extends Dto, M extends Model> {
    D convert(M model);

    M convert(D dto);
}
