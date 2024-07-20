
package com.enes.fitnes_api.handler;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.enes.fitnes_api.expectations.AllReadyExists;
import com.enes.fitnes_api.expectations.NotFoundExpection;

@ControllerAdvice
public class GlobalExpectionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<GenericResponse<Object>> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR)
                .body(GenericResponse.builder().success(false).message(e.getMessage()).build());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<GenericResponse<Object>> handleException(Exception e) {
        return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR)
                .body(GenericResponse.builder().success(false).message(e.getMessage()).build());
    }

    @ExceptionHandler({ AllReadyExists.class })
    public ResponseEntity<GenericResponse<Object>> handleAllReadyExists(AllReadyExists e) {
        return ResponseEntity.status(Response.SC_BAD_REQUEST)
                .body(GenericResponse.builder().success(false).message(e.getMessage()).build());
    }

    @ExceptionHandler({ NotFoundExpection.class })
    public ResponseEntity<GenericResponse<Object>> handleNotFoundExpection(NotFoundExpection e) {
        return ResponseEntity.status(Response.SC_BAD_REQUEST)
                .body(GenericResponse.builder().success(false).message(e.getMessage()).build());
    }

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<GenericResponse<Object>> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(Response.SC_BAD_REQUEST)
                .body(GenericResponse.builder().success(false).message(e.getMessage()).build());
    }
}