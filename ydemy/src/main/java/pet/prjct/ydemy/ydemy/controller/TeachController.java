package pet.prjct.ydemy.ydemy.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
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

        if (courseAdding) {
            return "redirect:/teach";
        }

        theModel.addAttribute("message",
                "You already own the course with the title '" + courseCreation.getTitle() + "'.");
        return "teach-page";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") long id,
                               Model theModel) {
        Course course = courseService.findById(id);
        theModel.addAttribute("course", course);

        return "update-course-page";
    }

    @PostMapping("/update-course")
    public String updateCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model theModel) {
        if (bindingResult.hasErrors()) {
            theModel.addAttribute("message", "Some errors");

            return "update-course-page";
        }

        courseService.update(course);

        return "redirect:/teach";
    }
}
