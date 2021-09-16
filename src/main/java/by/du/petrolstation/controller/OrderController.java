package by.du.petrolstation.controller;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ModelAndView list() {
        final ModelAndView view = new ModelAndView("order/index");
        view.addObject("orders", orderFacade.findAll());
        return view;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView list(Authentication authentication) {
        final ModelAndView view = new ModelAndView("order/index");
        view.addObject("orders", orderFacade.findAll(authentication));
        return view;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView showDispensers() {
        ModelAndView view = new ModelAndView("order/add");
        view.addObject("dispensers", orderFacade.getAllDispensers());
        return view;
    }

    @GetMapping("/petrols")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView showPetrols(@RequestParam("dispenserId") Long id) {
        ModelAndView view = new ModelAndView("order/add");
        DispenserDto dispenser = orderFacade.getDispenser(id);
        view.addObject("dispensers", orderFacade.getAllDispensers());
        view.addObject("petrols", dispenser.getPetrols());
        return view;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView order(
            @RequestParam(defaultValue = "0") BigDecimal quantity,
            @RequestParam(defaultValue = "0") BigDecimal amount,
            @RequestParam String petrol
    ) {
        ModelAndView view = new ModelAndView("order/view");
        if (quantity.equals(BigDecimal.ZERO)) {
            view.addObject("order", orderFacade.getOrderByAmount(amount, petrol));

        } else {
            view.addObject("order", orderFacade.getOrderByQuantity(quantity, petrol));
        }
        return view;
    }

    @PostMapping("/pay")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView pay(@ModelAttribute OrderDto orderDto, Authentication authentication) {
        orderFacade.add(orderDto, authentication);
        return new ModelAndView("redirect:/order");
    }
}
