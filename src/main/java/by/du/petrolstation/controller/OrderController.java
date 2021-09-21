package by.du.petrolstation.controller;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.dto.OrderDto;
import by.du.petrolstation.facade.OrderFacade;
import by.du.petrolstation.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private static final String ERR_MSG = "errMsg";
    private static final String ORDERS = "orders";
    public static final String ORDER = "order";
    private final OrderFacade orderFacade;
    private final UserFacade userFacade;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ModelAndView list() {
        final ModelAndView view = new ModelAndView("order/indexManager");
        view.addObject(ORDERS, orderFacade.findAll());
        return view;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView list(Authentication authentication) {
        final ModelAndView view = new ModelAndView("order/index");
        view.addObject(ORDERS, orderFacade.findAll(authentication));
        return view;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView showDispensers(Model model) {
        ModelAndView view = new ModelAndView("order/add");
        if (model.containsAttribute(ERR_MSG)) {
            view.addObject(Objects.requireNonNull(model.getAttribute(ERR_MSG)));
        }
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
            view.addObject(ORDER, orderFacade.getOrderByAmount(amount, petrol));
        } else {
            view.addObject(ORDER, orderFacade.getOrderByQuantity(quantity, petrol));
        }
        return view;
    }

    @PostMapping("/pay")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView pay(@ModelAttribute OrderDto orderDto, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (!orderFacade.checkOrder(orderDto)) {
            redirectAttributes.addFlashAttribute(ERR_MSG, "you can't buy petrol");
            return new ModelAndView("redirect:/order/add");
        }
        orderFacade.add(orderDto, authentication);
        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("order/delete");
        view.addObject(ORDER, orderFacade.findById(id));
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView deletePost(@RequestParam("id") Long id, Authentication authentication) {
        final ModelAndView view = new ModelAndView();
        orderFacade.deleteById(id);
        if (userFacade.isClient(authentication)) {
            view.setViewName("redirect:");
        } else {
            view.setViewName("redirect:list");
        }
        return view;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView updateGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("/order/edit");
        view.addObject(ORDER, orderFacade.findById(id));
        return view;
    }

    @PostMapping("/edit")
    public ModelAndView editOrder(@ModelAttribute OrderDto orderDto, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return new ModelAndView("petrol/edit");
        }
        final ModelAndView view = new ModelAndView();
        orderFacade.updateOrder(orderDto);
        if (userFacade.isClient(authentication)) {
            view.setViewName("redirect:");
        } else {
            view.setViewName("redirect:list");
        }
        return view;
    }
}
