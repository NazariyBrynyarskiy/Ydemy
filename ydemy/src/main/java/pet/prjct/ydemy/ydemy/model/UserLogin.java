package pet.prjct.ydemy.ydemy.model;


import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    @NotEmpty(message = "is required")
    @Size(min = 1, message = "is required")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String name;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String surname;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @NotEmpty(message = "is required")
    @Size(min = 6, message = "is required")
    private String password;

}
