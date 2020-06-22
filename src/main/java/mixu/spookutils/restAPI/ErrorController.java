package mixu.spookutils.restAPI;

import mixu.spookutils.SpookUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    public ErrorController() {}

    @GetMapping("/error")
    public Error handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = exception.getMessage();
        SpookUtils.logger.info(statusCode);
        return new Error(statusCode, exceptionMessage);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
