package pet.prjct.ydemy.ydemy.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.VideoCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.service.CourseService;
import pet.prjct.ydemy.ydemy.service.VideoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
@RequestMapping("/teach")
public class TeachController {

    private final CourseService courseService;
    private final VideoService videoService;
    private long courseId;

    @Autowired
    public TeachController(CourseService courseService,
                           VideoService videoService) {
        this.courseService = courseService;
        this.videoService = videoService;
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
            return "home/teach-page";
        }

        boolean courseAdding = courseService.save(courseCreation);

        if (courseAdding) {
            return "redirect:/teach";
        }

        theModel.addAttribute("message",
                "You already own the course with the title '" + courseCreation.getTitle() + "'.");
        return "home/teach-page";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") long id,
                               Model theModel) {
        Course course = courseService.findById(id);
        courseId = id;
        theModel.addAttribute("course", course);
        theModel.addAttribute("video", new VideoCreation());

        return "course/update-course-page";
    }

    @PostMapping("/update-course")
    public String updateCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model theModel) {

        if (bindingResult.hasErrors()) {
            theModel.addAttribute("message", "Some errors");

            return "course/update-course-page";
        }

        courseService.update(course);

        return "redirect:/teach";
    }

    @GetMapping("/delete")
    public String delete(@Valid @ModelAttribute("course") Course course) {

        courseService.delete(course);

        return "redirect:/teach";
    }

    @PostMapping("/upload")
    public String uploadVideo(@ModelAttribute("video") VideoCreation videoCreation,
                              @RequestParam("videoFile") MultipartFile videoFile) {

        final String uploadDirectory = "/Users/nazariybrynyarsky/Desktop/Java/Spring/Ydemy/ydemy/src/main/resources/video";

        String originalFileName = String.valueOf(courseId)
                .concat(Objects.requireNonNull(videoFile.getOriginalFilename()));

        Path filePath = Paths.get(uploadDirectory, originalFileName);

        try {
            Files.write(filePath, videoFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        videoCreation.setPath(uploadDirectory);
        videoService.save(videoCreation, courseId);

        return "redirect:/teach";
    }

}
