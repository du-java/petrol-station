package by.du.petrolstation.service;

import by.du.petrolstation.dto.Dto;
import by.du.petrolstation.exception.NotFoundException;
import by.du.petrolstation.model.Model;
import by.du.petrolstation.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractRestService<D extends Dto, M extends Model> implements RestService<D, M> {

    private final JpaRepository<M, Long> jpaRepository;
    private final Converter<D, M> converter;

    @Override
    public D findById(Long id) {
        final M model = getById(id);
        return converter.convert(model);
    }

    private M getById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<D> findAll() {
        return jpaRepository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public D update(D dto) {
        getById(dto.getId());
        final M updated = converter.convert(dto);
        final M saved = jpaRepository.save(updated);
        return converter.convert(saved);
    }

    @Override
    public D create(D dto) {
        final M converted = converter.convert(dto);
        final M saved = jpaRepository.save(converted);
        return converter.convert(saved);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
