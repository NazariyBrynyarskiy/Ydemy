package pet.prjct.ydemy.ydemy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.service.CourseService;

@Controller
public class MainController {

    private CourseService courseService;

    @Autowired
    public MainController(CourseService courseService) {
        this.courseService = courseService;
    }

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
        theModel.addAttribute("courses", courseService.findAllByCookieUsername());

        return "teach-page";
    }

}
