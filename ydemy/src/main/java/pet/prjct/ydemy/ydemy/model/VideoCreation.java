package pet.prjct.ydemy.ydemy.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoCreation {

    @NotEmpty(message = "is required")
    @Size(min = 1, message = "is required")
    private String title;

    private String path;

}
