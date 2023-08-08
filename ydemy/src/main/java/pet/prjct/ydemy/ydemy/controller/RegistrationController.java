package pet.prjct.ydemy.ydemy.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pet.prjct.ydemy.ydemy.model.UserLogin;
import pet.prjct.ydemy.ydemy.model.entity.User;
import pet.prjct.ydemy.ydemy.service.UserService;

import java.util.logging.Logger;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());
    private UserService userService;

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

        return "register/registration-form";
    }
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userLogin") UserLogin userLogin,
            BindingResult theBindingResult,
            Model theModel) {

        if (theBindingResult.hasErrors()) {
            return "register/registration-form";
        }

        if (userService.containsUserByUsername(userLogin.getUsername())) {
            theModel.addAttribute("registrationError", "Username already exists");
            return "register/registration-form";
        }

        userService.save(userLogin);

        return "register/registration-confirmation";
    }
}
