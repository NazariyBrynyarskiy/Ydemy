package pet.prjct.ydemy.ydemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/auth")
    public String auth() {
        return "auth-form";
    }

    @GetMapping("/invalid-page")
    public String getInvalidPage() {
        return "invalid-page";
    }
}
