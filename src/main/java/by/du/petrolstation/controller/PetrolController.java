package by.du.petrolstation.controller;

import by.du.petrolstation.dto.PetrolDto;
import by.du.petrolstation.facade.PetrolFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/petrol")
@RequiredArgsConstructor
public class PetrolController {

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
        view.setViewName("petrol/index");
        view.addObject("petrols", petrolFacade.findAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("petrol/add");
        return view;
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute PetrolDto petrolDto) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/petrol");
        petrolFacade.add(petrolDto);
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("petrol/edit");

        view.addObject("petrol", petrolFacade.findById(id));

        return view;
    }

    @PostMapping("/edit")
    public ModelAndView editPost(@Valid @ModelAttribute PetrolDto petrolDto) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/petrol");
        petrolFacade.update(petrolDto);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("petrol/delete");

        view.addObject("petrol", petrolFacade.findById(id));

        return view;
    }

    @PostMapping("/delete")
    public ModelAndView deletePost(@RequestParam("id") Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/petrol");
        petrolFacade.deleteById(id);
        return view;
    }
}
