package tw.sanjiheart.util;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(NullPointerException.class)
  public void handleException(NullPointerException e, HttpServletResponse response) throws IOException {
    e.printStackTrace();
    logger.error(e.getMessage());
    response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public void handleException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
    e.printStackTrace();
    logger.error(e.getMessage());
    response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  @ExceptionHandler(NoSuchElementException.class)
  public void handleException(NoSuchElementException e, HttpServletResponse response) throws IOException {
    e.printStackTrace();
    logger.error(e.getMessage());
    response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
  }

  @ExceptionHandler(HttpException.class)
  public void handleException(HttpException e, HttpServletResponse response) throws IOException {
    e.printStackTrace();
    logger.error(e.getMessage());
    response.sendError(e.getStatusCode(), e.getMessage());
  }

}
