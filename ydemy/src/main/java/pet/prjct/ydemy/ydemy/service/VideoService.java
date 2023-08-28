package pet.prjct.ydemy.ydemy.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class VideoService {


    public String getVideo() {
        File file = new File("/Users/nazariybrynyarsky/Desktop/Ydemy/MyData/IMG_1509.MOV");

        return file.getPath();
    }

}
