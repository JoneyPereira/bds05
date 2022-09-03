package com.devsuperior.movieflix.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError erro = new ValidationError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        erro.setError("Recurso não validado!");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            erro.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
}
