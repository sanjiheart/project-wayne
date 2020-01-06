package tw.sanjiheart.util;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

  private static final long serialVersionUID = 4357185667866789448L;

  private HttpStatus status;
  private String message;

  public HttpException(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatusCode() {
    return status.value();
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
