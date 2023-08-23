package pet.prjct.ydemy.ydemy.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pet.prjct.ydemy.ydemy.dao.cookie.Cookie;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.service.CourseService;

@Controller
@RequestMapping("/teach")
public class TeachController {

    private CourseService courseService;

    @Autowired
    public TeachController(CourseService courseService) {
        this.courseService = courseService;
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @PostMapping("/createCourse")
    public String createCourse(
            @Valid @ModelAttribute("courseCreation") CourseCreation courseCreation,
            BindingResult bindingResult,
            Model theModel) {

        if (bindingResult.hasErrors()) {
            return "teach-page";
        }

        boolean courseAdding = courseService.save(courseCreation);

        theModel.addAttribute("message",
                (courseAdding) ? "Course was added." :
                        "You already own the course with the title '" + courseCreation.getTitle() + "'.");

        theModel.addAttribute("courseCreation", new CourseCreation());

        return "teach-page";
    }
}
