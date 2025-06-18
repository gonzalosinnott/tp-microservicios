package sinnott.concesionaria.commons.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.ConstraintViolationException;
import sinnott.concesionaria.commons.models.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(
            ResponseStatusException ex,
            HttpServletRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.of(
            ex.getStatusCode().value(),
            ex.getStatusCode().toString(),
            ex.getReason(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            Exception ex,
            HttpServletRequest request) {
        
        List<String> details;
        if (ex instanceof MethodArgumentNotValidException) {
            details = ((MethodArgumentNotValidException) ex).getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        } else {
            details = ((BindException) ex).getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        }

        ErrorResponse errorResponse = ErrorResponse.of(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            "Error en la validación de campos",
            request.getRequestURI(),
            details
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {
        
        List<String> details = ex.getConstraintViolations()
            .stream()
            .map(violation -> {
                String path = violation.getPropertyPath().toString();
                // Get the field name (last part of the path)
                String field = path.contains(".") ? 
                    path.substring(path.lastIndexOf(".") + 1) : path;
                
                // Format field name to be more readable
                field = field.replace("_", " ");
                
                return field + ": " + violation.getMessage();
            })
            .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            "Error en la validación de campos",
            request.getRequestURI(),
            details
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {
        
        List<String> details = List.of(ex.getMostSpecificCause().getMessage());

        ErrorResponse errorResponse = ErrorResponse.of(
            HttpStatus.CONFLICT.value(),
            "Error de Integridad",
            "Error de integridad en la base de datos",
            request.getRequestURI(),
            details
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(
            FeignException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(
            HttpStatus.BAD_GATEWAY.value(),
            "Feign Client Error",
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception ex,
            HttpServletRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return ResponseEntity.internalServerError().body(errorResponse);
    }
} 