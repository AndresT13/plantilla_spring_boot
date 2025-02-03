package com.progamming.lite.thinking.tareapractica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejador para respuestas exitosas con código 200 (OK)
    @ExceptionHandler(ResponseExceptions.SuccessResponseException.class)
    public ResponseEntity<Object> handleOk(ResponseExceptions.SuccessResponseException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }

    // Manejador para respuestas exitosas con código 201 (Created)
    @ExceptionHandler(ResponseExceptions.CreatedResponseException.class)
    public ResponseEntity<Void> handleCreated(ResponseExceptions.CreatedResponseException exception) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Manejador para respuestas exitosas con código 204 (No Content)
    @ExceptionHandler(ResponseExceptions.NoContentResponseException.class)
    public ResponseEntity<Void> handleNoContent(ResponseExceptions.NoContentResponseException exception) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Otros manejadores para excepciones generales pueden ir aquí, por ejemplo:
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Object> handleGeneralException(Exception exception) {
    //     return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
