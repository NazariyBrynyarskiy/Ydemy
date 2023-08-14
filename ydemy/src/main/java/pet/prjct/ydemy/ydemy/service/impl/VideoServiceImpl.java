package pet.prjct.ydemy.ydemy.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pet.prjct.ydemy.ydemy.service.VideoService;

import java.io.File;

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String getVideo() {
        File file = new File("/Users/nazariybrynyarsky/Desktop/Ydemy/MyData/IMG_1509.MOV");

        return file.getPath();
    }
}
