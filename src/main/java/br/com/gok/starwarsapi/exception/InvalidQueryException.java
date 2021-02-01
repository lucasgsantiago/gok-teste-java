package br.com.gok.starwarsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidQueryException extends APIException {
  private static final long serialVersionUID = 1L;

  public InvalidQueryException(Throwable cause) {
    super(cause);
  }

  public InvalidQueryException(ErrorMessage error) {
    super(error);
  }

  public InvalidQueryException(String error) {
    super(error);
  }

}
