package pet.prjct.ydemy.ydemy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pet.prjct.ydemy.ydemy.model.CourseCreation;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/settings")
    public String getIndexPage(Model model) {
        model.addAttribute("message", "Hello Spring!");

        return "settings";
    }

    @GetMapping("/teach")
    public String teachPage(Model theModel) {
        theModel.addAttribute("courseCreation", new CourseCreation());

        return "teach-page";
    }

}
