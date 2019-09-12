package ch.cern.springcampus.demospring.controller;

import ch.cern.springcampus.demospring.bean.User;
import ch.cern.springcampus.demospring.dto.RegistrationForm;
import ch.cern.springcampus.demospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(final UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @SuppressWarnings("unused")
    @GetMapping("/register")
    public String register(final RegistrationForm registrationForm) {
        return "register";
    }

    @PostMapping("/register")
    public String register(final @Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                           final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = createUserInstance(registrationForm);
        userService.createUser(user);
        return "redirect:/task/list";
    }

    private User createUserInstance(final RegistrationForm registrationForm) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setPassword(registrationForm.getPassword());
        return user;
    }
}
