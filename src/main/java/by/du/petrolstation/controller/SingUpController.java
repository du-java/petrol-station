package by.du.petrolstation.controller;

import by.du.petrolstation.dto.SingUpUserDto;
import by.du.petrolstation.service.SingUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/singup")
public class SingUpController {

    private final SingUpService singUpService;

    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView view = new ModelAndView("singup/index");
        view.addObject("singUpUserDto", SingUpUserDto.builder().build());
        return view;
    }

    @PostMapping
    public ModelAndView createUser(@Valid @ModelAttribute SingUpUserDto singUpUserDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView view = new ModelAndView("singup/index");
            view.addObject("singUpUserDto", singUpUserDto);
            return view;
        }
        try {
            singUpService.createUser(singUpUserDto);
            return new ModelAndView("redirect:/login");
        } catch (IllegalArgumentException ex) {
            ModelAndView view = new ModelAndView("singup/index");
            view.addObject("errors", ex.getMessage());
            view.addObject("singUpUserDto", singUpUserDto);
            return view;
        }
    }
}
