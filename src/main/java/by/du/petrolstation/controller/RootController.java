package by.du.petrolstation.controller;

import by.du.petrolstation.facade.RootFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class RootController {

    private final RootFacade rootFacade;

    @GetMapping("/")
    public ModelAndView root() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("dispensers", rootFacade.findAllDispenser());
        view.addObject("tanks", rootFacade.findAllTank());
        view.addObject("petrols", rootFacade.findAllPetrol());
        view.addObject("station", rootFacade.find());
        return view;
    }
}
