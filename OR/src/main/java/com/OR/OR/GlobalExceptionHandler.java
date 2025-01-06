package com.OR.OR;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.ServiceUnavailableException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Uspjelo
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseC<String>> handleNoResourceFound(NoResourceFoundException ex) {
        ResponseC<String> response = new ResponseC<>("NOT_FOUND", "The requested resource was not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    //kad unosiom id kao slova
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ResponseC<String>> handleNumberFormatException(NumberFormatException ex) {
        ResponseC<String> response = new ResponseC<>("BAD_REQUEST", "Unijeta vrijednost treba biti broj, a ne slova/Unijeta vrijednost je prevelika", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    //unos kljuca koji je u bazi vec
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ResponseC<String>> handleDuplicateKey(DuplicateKeyException ex) {
        ResponseC<String> response = new ResponseC<>("BAD_REQUEST", "Objekt s ovim kljucem id/naziv postoji u bazi, unesi neku vrijednost koja ne postoji", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    //unos stringa umjesto inta u add
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseC<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "Unijeta vrijednost treba biti broj, a ne slova/Unijeta vrijednost je prevelika";
        ResponseC<String> response = new ResponseC<>("BAD_REQUEST", errorMessage, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    

    //aaaa
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseC<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("Unijeta vrijednost treba biti broj, a ne slova/Unijeta vrijednost je prevelika");
        ResponseC<String> response = new ResponseC<>("BAD_REQUEST", errorMessage, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseC<String>> handleBadRequest(BadRequestException ex) {
        ResponseC<String> response = new ResponseC<>("BAD_REQUEST", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }




    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ResponseC<String>> handleServiceUnavailable(ServiceUnavailableException ex) {
        ResponseC<String> response = new ResponseC<>("SERVICE_UNAVAILABLE", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }



    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ResponseC<String>> handleMethodNotAllowed(MethodNotAllowedException ex) {
        ResponseC<String> response = new ResponseC<>("METHOD_NOT_ALLOWED", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }


    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ResponseC<String>> handleTimeout(TimeoutException ex) {
        ResponseC<String> response = new ResponseC<>("TIMEOUT", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseC<String>> handleGenericException(Exception ex) {
        ex.printStackTrace();
        ResponseC<String> response = new ResponseC<>("INTERNAL_SERVER_ERROR", "An unexpectedeee error occurred", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}