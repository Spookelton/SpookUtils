package net.spookelton.spookutils.restAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
public class ErrorHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public Error handleNoHandler(NoHandlerFoundException e) {
        return new Error(404, "Not found");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(Exception e) {
        e.printStackTrace();
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
