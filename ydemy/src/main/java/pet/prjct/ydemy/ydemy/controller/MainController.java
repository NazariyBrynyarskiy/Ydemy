package pet.prjct.ydemy.ydemy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.VideoCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.service.CourseService;

import java.util.List;

@Controller
public class MainController {

    private final CourseService courseService;
    private boolean areCoursesPresent = false;


    @Autowired
    public MainController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String getHomePage(Model theModel) {
        theModel.addAttribute("courseCount", courseService.count());

        return "home/index";
    }

    @GetMapping("/find")
    public String findCourses(Model theModel,
                              @RequestParam("title") String title) {
        List<Course> findAllByParameter = courseService.findAllByParameter(title);

        if (!findAllByParameter.isEmpty()) {
            areCoursesPresent = true;
            theModel.addAttribute("findCourses", findAllByParameter);
        }

        theModel.addAttribute("areCoursesPresent", areCoursesPresent);

        return "home/index";
    }

    @GetMapping("/settings")
    public String getIndexPage(Model model) {
        model.addAttribute("message", "Hello Spring!");

        return "home/settings";
    }

    @GetMapping("/teach")
    public String teachPage(Model theModel) {
        theModel.addAttribute("courseCreation", new CourseCreation());
        theModel.addAttribute("courses", courseService.findAll());

        return "home/teach-page";
    }

}
