package sg.edu.nus.iss.springdemo.exception;

import java.io.IOException;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;


import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleIOException(HttpServletRequest request, Exception ex) {
        return new ErrorInfo(request.getRequestURI(), "IO Error: " + ex.getMessage());
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public String handleConnectionError(Exception ex) {

        LOGGER.error(ex.getMessage(), ex);

        return "connect_error";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @ExceptionHandler(Exception.class)
    // public String handleGeneralError(HttpServletRequest request,
    //         HttpServletResponse response, Exception ex) {

    //     LOGGER.error(ex.getMessage(), ex);

    //     // do something with request and response

    //     return "general_error";
    // }
}
