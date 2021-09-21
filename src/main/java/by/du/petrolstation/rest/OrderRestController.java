package by.du.petrolstation.rest;

import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.model.Order;
import by.du.petrolstation.service.impl.OrderRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/order")
public class OrderRestController extends AbstractRestController<OrderDto, Order> {

    private final OrderRestService orderRestService;

    public OrderRestController(OrderRestService orderRestService) {
        super(orderRestService);
        this.orderRestService = orderRestService;
    }

    @PatchMapping("/")
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRestService.patch(dto));
    }
}
