package pet.prjct.ydemy.ydemy.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import pet.prjct.ydemy.ydemy.service.VideoService;
import reactor.core.publisher.Mono;

@RestController
public class VideoRestController {

    private VideoService videoService;

    @Autowired
    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;
    }


    @GetMapping("/show-video/{title}")
    public Mono<Resource> getVideo(@PathVariable String title) {
        return videoService.getVideo(title);
    }

}
