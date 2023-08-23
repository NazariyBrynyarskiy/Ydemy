package pet.prjct.ydemy.ydemy.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreation {

    @NotEmpty(message = "is required")
    @Size(min = 1, message = "is required")
    private String title;

    @Min(value = 0, message = "must be greater than or equal to 0")
    private int price;

    private String description;

}
