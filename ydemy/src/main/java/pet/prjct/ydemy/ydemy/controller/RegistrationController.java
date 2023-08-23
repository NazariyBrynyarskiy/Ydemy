package pet.prjct.ydemy.ydemy.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pet.prjct.ydemy.ydemy.model.UserLogin;
import pet.prjct.ydemy.ydemy.service.UserService;



@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;
    private final String REGISTRATION_FORM = "register/registration-form";

    @Autowired
    public RegistrationController(UserService service) {
        userService = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("userLogin", new UserLogin());

        return REGISTRATION_FORM;
    }
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userLogin") UserLogin userLogin,
            BindingResult theBindingResult,
            Model theModel) {

        if (theBindingResult.hasErrors()) {
            return REGISTRATION_FORM;
        }

        if (userService.containsUserByUsername(userLogin.getUsername())) {
            theModel.addAttribute("registrationError",
                    "Username already exists");
            if (userService.containsUserByEmail(userLogin.getEmail())) {
                theModel.addAttribute("registrationError",
                        "Username and email already exists.");
            }
            return REGISTRATION_FORM;
        }

        if (userService.containsUserByEmail(userLogin.getEmail())) {
            theModel.addAttribute("registrationError",
                    "Email already exists.");
            return REGISTRATION_FORM;
        }

        userService.save(userLogin);

        return "register/registration-confirmation";
    }
}
