package by.du.petrolstation.controller;

import by.du.petrolstation.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/")
    public ModelAndView rootWith() {
        return root();
    }

    @GetMapping("")
    public ModelAndView rootWithout() {
        return root();
    }

    private ModelAndView root() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("currency/index");
        view.addObject("currencies", currencyService.getCurrencyList());
        return view;
    }
}
