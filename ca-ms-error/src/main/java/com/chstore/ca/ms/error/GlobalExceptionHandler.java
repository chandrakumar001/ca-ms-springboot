package com.chstore.ca.ms.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERR3333 = "3333";
    private static final String ERR2222 = "2222";
    private static final String ERR1111 = "1111";
    private static final String AND_TOKEN_IS_NOT_FOUND = " and Token is not found";
    private static final String MOBILE_ID = "mobileId ";
    private static final String INVALID_PASSWORD = "Invalid Password";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {

        return CommonExceptionBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, ex, "100");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
            final CustomerNotFoundException CustomerNotFoundException) {

        return CommonExceptionBuilder.build(HttpStatus.NOT_FOUND, CustomerNotFoundException, "100");
    }

    @ExceptionHandler({CustomerServiceException.class})
    public ResponseEntity<ErrorResponse> handleCustomerServiceException(
            final CustomerServiceException CustomerServiceException) {

        return CommonExceptionBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, CustomerServiceException, "100");
    }

    @ExceptionHandler({CustomerServiceValidationException.class})
    public ResponseEntity<ErrorResponse> handleCustomerServiceValidationException(
            final CustomerServiceValidationException CustomerServiceValidationException) {

        return CommonExceptionBuilder.build(HttpStatus.BAD_REQUEST, CustomerServiceValidationException, "100");
    }

    @ExceptionHandler({CustomerAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            final CustomerAlreadyExistsException CustomerServiceValidationException) {

        return CommonExceptionBuilder.build(HttpStatus.CONFLICT, CustomerServiceValidationException, "100");
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAuthorizationException(final AccessDeniedException ex) {

        return CommonExceptionBuilder.build(HttpStatus.BAD_REQUEST, ex, "100");
    }

    @ExceptionHandler({BussinessServiceException.class})
    public ResponseEntity<ErrorResponse> handleBussinessServiceException(final BussinessServiceException ex) {

        return CommonExceptionBuilder.build(HttpStatus.BAD_REQUEST, ex, "100");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleValidationExceptions(MethodArgumentNotValidException ex) {

        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(GlobalExceptionHandler::getResponse)
                .findFirst()
                .get();
    }

    private static ExceptionResponse getResponse(ObjectError x) {
        return new ExceptionResponse(x.getCode(), x.getDefaultMessage());
    }

    @ExceptionHandler(InvalidPassword.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(InvalidPassword ex) {


        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ERR1111);
        response.setErrorMessage(MOBILE_ID + ex.id + INVALID_PASSWORD);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicationUserIDNotFound.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ApplicationUserIDNotFound ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ERR2222);
        response.setErrorMessage(MOBILE_ID + ex.id);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationUserTokenNotFound.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ApplicationUserTokenNotFound ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ERR3333);
        response.setErrorMessage(MOBILE_ID + ex.id + AND_TOKEN_IS_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
