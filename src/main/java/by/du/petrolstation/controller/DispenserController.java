package by.du.petrolstation.controller;

import by.du.petrolstation.dto.DispenserDto;
import by.du.petrolstation.facade.DispenserFacade;
import by.du.petrolstation.facade.PetrolFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dispenser")
@RequiredArgsConstructor
public class DispenserController {

    private final DispenserFacade dispenserFacade;
    private final PetrolFacade petrolFacade;

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
        view.setViewName("dispenser/index");
        view.addObject("dispensers", dispenserFacade.findAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("dispenser/add");
        view.addObject("petrols", petrolFacade.findAll());
        return view;
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute DispenserDto dispenserDto) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/dispenser");
        dispenserFacade.add(dispenserDto);
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("dispenser/edit");

        view.addObject("petrols", petrolFacade.findAll());
        view.addObject("dispenser", dispenserFacade.findById(id));

        return view;
    }

    @PostMapping("/edit")
    public ModelAndView editPost(@ModelAttribute DispenserDto dispenserDto) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/dispenser");
        dispenserFacade.update(dispenserDto);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("dispenser/delete");

        view.addObject("petrols", petrolFacade.findAll());
        view.addObject("dispenser", dispenserFacade.findById(id));

        return view;
    }

    @PostMapping("/delete")
    public ModelAndView deletePost(@RequestParam("id") Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/dispenser");
        dispenserFacade.deleteById(id);
        return view;
    }
}
