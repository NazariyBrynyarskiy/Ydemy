package pet.prjct.ydemy.ydemy.controller.videofiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pet.prjct.ydemy.ydemy.service.VideoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class VideoController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/get-video")
    public String getVideo(Model theModel) {
        theModel.addAttribute("video", videoService.getVideo());

        return "settings";
    }

    private static final String UPLOAD_DIR = "/path/to/upload/directory/";

    @PostMapping("/upload-video")
    public ResponseEntity<String> uploadVideo(@RequestParam("videoFile") MultipartFile videoFile) {
        try {
            String filename = UUID.randomUUID().toString() + ".mp4"; // Генеруємо унікальне ім'я файлу
            Path filePath = Paths.get(UPLOAD_DIR, filename);

            Files.copy(videoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String videoUrl = "/uploaded-videos/" + filename; // Повертаємо URL до завантаженого відео

            return ResponseEntity.ok(videoUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
