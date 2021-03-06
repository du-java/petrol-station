package by.du.petrolstation.controller;

import by.du.petrolstation.dto.TankDto;
import by.du.petrolstation.facade.PetrolFacade;
import by.du.petrolstation.facade.TankFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/tank")
@RequiredArgsConstructor
public class TankController {

    private final TankFacade tankFacade;
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
        view.setViewName("tank/index");
        view.addObject("tanks", tankFacade.findAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("tank/add");
        view.addObject("petrols", petrolFacade.findAll());
        view.addObject("tankDto", TankDto.builder().build());
        return view;
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute TankDto tankDto, BindingResult result) {
        if (result.hasErrors()) {
            final ModelAndView view = new ModelAndView("tank/add");
            view.addObject("petrols", petrolFacade.findAll());
            return view;
        }
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/tank");
        tankFacade.add(tankDto);
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("tank/edit");

        view.addObject("petrols", petrolFacade.findAll());
        view.addObject("tankDto", tankFacade.findById(id));

        return view;
    }

    @PostMapping("/edit")
    public ModelAndView editPost(@Valid @ModelAttribute TankDto tankDto, BindingResult result) {
        if (result.hasErrors()) {
            final ModelAndView view = new ModelAndView("tank/edit");
            view.addObject("petrols", petrolFacade.findAll());
            return view;
        }
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/tank");
        tankFacade.update(tankDto);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("tank/delete");

        view.addObject("tank", tankFacade.findById(id));

        return view;
    }

    @PostMapping("/delete")
    public ModelAndView deletePost(@RequestParam("id") Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/tank");
        tankFacade.deleteById(id);
        return view;
    }
}
