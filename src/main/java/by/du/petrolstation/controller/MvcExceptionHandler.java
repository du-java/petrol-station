package by.du.petrolstation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class MvcExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = BindException.class)
    public ModelAndView exception(BindException exception) {
        ModelAndView view = new ModelAndView("error.html");
        Map<String, List<String>> errorMap = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(e -> messageSource.getMessage(e, LocaleContextHolder.getLocale()),
                                Collectors.toList())));
        view.addObject("errors", errorMap);
        return view;
    }
}
