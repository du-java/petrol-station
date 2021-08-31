package by.du.petrolstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {
    @GetMapping("/")
    public ModelAndView root() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }
}
