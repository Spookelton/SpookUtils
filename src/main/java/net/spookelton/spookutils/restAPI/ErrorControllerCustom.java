package net.spookelton.spookutils.restAPI;

import net.spookelton.spookutils.SpookUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorControllerCustom implements ErrorController {
    @RequestMapping("/error")
    public Error handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String exceptionMessage = exception.getMessage();
        SpookUtils.logger.info(statusCode);
        return new Error(statusCode, exceptionMessage);
    }
}
