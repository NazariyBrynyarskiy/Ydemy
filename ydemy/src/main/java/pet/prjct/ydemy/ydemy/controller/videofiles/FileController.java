package pet.prjct.ydemy.ydemy.controller.videofiles;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class FileController {

    @GetMapping("/get-file-info")
    @ResponseBody
    public byte[] getFileInfo() throws IOException {
        File file = new File("/Users/nazariybrynyarsky/Desktop/Ydemy/MyData/file.txt");
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            return fileBytes;
        }
    }

}
