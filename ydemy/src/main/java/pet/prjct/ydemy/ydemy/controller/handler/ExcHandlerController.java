package pet.prjct.ydemy.ydemy.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pet.prjct.ydemy.ydemy.exception.CourseNotFoundException;
import pet.prjct.ydemy.ydemy.model.ModelResponseHandler;


@ControllerAdvice
public class ExcHandlerController {

    @ExceptionHandler
    public ResponseEntity<ModelResponseHandler> personExceptionHandler(CourseNotFoundException exc) {
        ModelResponseHandler modelResponseHandler = new ModelResponseHandler();

        modelResponseHandler.setStatus(HttpStatus.NOT_FOUND.value());
        modelResponseHandler.setMessage(exc.getMessage());
        modelResponseHandler.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(modelResponseHandler, HttpStatus.NOT_FOUND);
    }

}

