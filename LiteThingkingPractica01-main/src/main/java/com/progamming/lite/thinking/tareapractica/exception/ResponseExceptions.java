package com.progamming.lite.thinking.tareapractica.exception;

public class ResponseExceptions {


    // Excepción para respuesta exitosa 200 (OK)
    public static class SuccessResponseException extends RuntimeException {
        public SuccessResponseException(String message) {
            super(message);
        }
    }

    // Excepción para respuesta exitosa 201 (Created)
    public static class CreatedResponseException extends RuntimeException {
        public CreatedResponseException(String message) {
            super(message);
        }
    }

    // Excepción para respuesta exitosa 204 (No Content)
    public static class NoContentResponseException extends RuntimeException {
        public NoContentResponseException(String message) {
            super(message);
        }
    }
}
