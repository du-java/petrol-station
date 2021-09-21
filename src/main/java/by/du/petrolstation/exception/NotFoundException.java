package by.du.petrolstation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private Long id;
    private String name;

    public NotFoundException(Long id) {
        super("Not found by id " + id);
        this.id = id;
    }

    public NotFoundException(String name) {
        super("Not found by name " + name);
        this.name = name;
    }
}
