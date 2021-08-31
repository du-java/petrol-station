package by.du.petrolstation.controller;

import by.du.petrolstation.model.Petrol;
import by.du.petrolstation.repository.PetrolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/petrol")
@RequiredArgsConstructor
public class PetrolController {

    private final PetrolRepository petrolRepository;

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
        final List<Petrol> petrols = petrolRepository.findAll();
        view.addObject("petrols", petrols);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        final ModelAndView view = new ModelAndView();
        view.setViewName("petrol/add");
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("petrol/edit");

        final Petrol petrol = petrolRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        view.addObject(petrol);

        return view;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(@PathVariable Long id, @ModelAttribute Petrol editedPetrol) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/petrol");

        final Petrol petrol = petrolRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        BeanUtils.copyProperties(editedPetrol, petrol);
        petrol.setId(petrol.getId());
        petrolRepository.save(petrol);

        view.addObject(petrol);

        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGet(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("petrol/delete");

        final Petrol petrol = petrolRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        view.addObject(petrol);

        return view;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deletePost(@PathVariable Long id) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/petrol");

        if (petrolRepository.existsById(id)) {
            // TODO: cascade deletion
            petrolRepository.deleteById(id);
        }

        return view;
    }
}
