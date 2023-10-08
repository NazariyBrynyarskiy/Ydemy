package pet.prjct.ydemy.ydemy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoController {

    @GetMapping("/video")
    public String getPage() {


        return "course/video-test";
    }

    @GetMapping("/create")
    public String getVideoCreatePage() {
        return "course/video";
    }

}
