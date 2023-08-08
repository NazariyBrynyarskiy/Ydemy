package pet.prjct.ydemy.ydemy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("helloSpring", "Hello Spring!");

        return "index";
    }

    @GetMapping("/leaders")
    public String getManagerPage() {
        return "managers";
    }

    @GetMapping("/systems")
    public String getAdminPage() {
        return "admins";
    }

}
