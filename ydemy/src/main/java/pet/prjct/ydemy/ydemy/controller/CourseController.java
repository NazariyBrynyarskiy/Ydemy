package pet.prjct.ydemy.ydemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pet.prjct.ydemy.ydemy.exception.CourseNotFoundException;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.service.CourseService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course")
    public String getCoursePage(@RequestParam("courseId") long id,
                                Model theModel) {
        Course course = courseService.findById(id);

        if (course == null) {
            throw new CourseNotFoundException("Course was not found.");
        }

        theModel.addAttribute("course", course);

        return "course/course";
    }

}
