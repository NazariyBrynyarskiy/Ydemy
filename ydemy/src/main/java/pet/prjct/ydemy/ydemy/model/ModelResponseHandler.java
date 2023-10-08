package pet.prjct.ydemy.ydemy.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelResponseHandler {

    private int status;
    private String message;
    private long timeStamp;

}
