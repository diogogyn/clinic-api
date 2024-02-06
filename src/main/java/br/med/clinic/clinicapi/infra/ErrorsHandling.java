package br.med.clinic.clinicapi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlingError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlingError400(MethodArgumentNotValidException ex){
        List<FieldError> fieldErrors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(fieldErrors.stream().map(ErrorValidationRecord::new).toList());
    }

    private record ErrorValidationRecord(String field, String message){
        public ErrorValidationRecord(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
