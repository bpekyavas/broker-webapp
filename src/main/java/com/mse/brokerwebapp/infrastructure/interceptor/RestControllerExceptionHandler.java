package com.mse.brokerwebapp.infrastructure.interceptor;

import com.mse.brokerwebapp.application.model.response.ErrorResponse;
import com.mse.brokerwebapp.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private static String NOT_FOUND = "101";
    private static String SAVE_OR_UPDATE_FAILED = "102";
    private static String VALIDATION_FAILED = "103";

    @ExceptionHandler({SecurityNotFoundBusinessException.class, TradesNotFoundBusinessException.class, TraderNotFoundBusinessException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundBusinessException(Exception ex) {
        return getErrorResponseResponseEntity(NOT_FOUND, ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PriceUpdateBusinessException.class, SecurityUpdateBusinessException.class, TradeSaveBusinessException.class})
    public ResponseEntity<ErrorResponse> handleUpdateFailedBusinessException(Exception ex) {
        return getErrorResponseResponseEntity(SAVE_OR_UPDATE_FAILED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StringValidationException.class)
    public ResponseEntity<ErrorResponse> handleStringValidationException(Exception ex) {
        return getErrorResponseResponseEntity(VALIDATION_FAILED, ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(String errorCode, Exception ex, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
