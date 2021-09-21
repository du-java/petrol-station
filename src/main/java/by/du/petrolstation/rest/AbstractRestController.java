package by.du.petrolstation.rest;

import by.du.petrolstation.dto.Dto;
import by.du.petrolstation.model.Model;
import by.du.petrolstation.service.RestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractRestController<D extends Dto, M extends Model> {

    private final RestService<D, M> service;

    @GetMapping("/")
    public ResponseEntity<List<D>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> get(@Positive @NotNull @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<D> create(@RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/")
    public ResponseEntity<D> update(@RequestBody D dto) {
        return ResponseEntity.accepted().body(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Positive @NotNull @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
