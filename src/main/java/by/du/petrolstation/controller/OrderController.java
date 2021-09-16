package by.du.petrolstation.controller;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.dto.SingUpUserDto;
import by.du.petrolstation.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping("/dispensers")
    public ModelAndView showDispensers() {
        ModelAndView view = new ModelAndView("order/add");
        view.addObject("dispensers", orderFacade.getAllDispensers());
        return view;
    }

    @GetMapping("/petrols")
    public ModelAndView showPetrols(@RequestParam("dispenserId") Long id) {
        ModelAndView view = new ModelAndView("order/add");
        DispenserDto dispenser = orderFacade.getDispenser(id);
        view.addObject("dispensers", orderFacade.getAllDispensers());
        view.addObject("petrols", dispenser.getPetrols());
        return view;
    }

    @PostMapping("/")
    public ModelAndView order(@RequestParam(defaultValue = "0") BigDecimal quantity, @RequestParam(defaultValue = "0") BigDecimal amount, @RequestParam String petrol) {
        ModelAndView view = new ModelAndView("order/view");
        if (quantity.equals(BigDecimal.ZERO)) {
            view.addObject("order", orderFacade.getOrderByAmount(amount, petrol));

        } else {
            view.addObject("order", orderFacade.getOrderByQuantity(quantity, petrol));
        }
        return view;
    }

    @PostMapping("/pay")
    public ModelAndView pay(@ModelAttribute OrderDto orderDto) {
        orderFacade.add(orderDto);
        return new ModelAndView("redirect:/order");
    }

}
