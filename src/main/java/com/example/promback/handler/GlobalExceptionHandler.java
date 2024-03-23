package com.example.promback.handler;

import com.example.promback.dto.ErrorDTO;
import com.example.promback.exception.InternalServerException;
import com.example.promback.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = { NotFoundException.class })
  protected ResponseEntity<ErrorDTO> handleNotFound(RuntimeException ex, WebRequest request) {
      ErrorDTO errorDTO = new ErrorDTO();
      errorDTO.setStatus("404");
      errorDTO.setReason(ex.getMessage());
      return ResponseEntity.status(404).body(errorDTO);
  }

  @ExceptionHandler(value = { InternalServerException.class })
  protected ResponseEntity<ErrorDTO> handleInternal(RuntimeException ex, WebRequest request) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setStatus("500");
    errorDTO.setReason(ex.getMessage());
    return ResponseEntity.status(500).body(errorDTO);
  }
}