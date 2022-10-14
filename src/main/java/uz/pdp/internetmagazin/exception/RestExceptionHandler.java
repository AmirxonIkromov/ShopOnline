package uz.pdp.internetmagazin.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.ErrorData;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = InputDataExistsException.class)
    public ResponseEntity<ApiResult<List<ErrorData>>> exceptionHandler(InputDataExistsException ex){
        ApiResult<List<ErrorData>> apiResult = ApiResult.failResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResult<List<ErrorData>>> exceptionHandler(NullPointerException ex){
        ApiResult<List<ErrorData>> apiResult = ApiResult.failResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiResult, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = MalformedJwtException.class)
    public ResponseEntity<ApiResult<List<ErrorData>>> exceptionHandler(MalformedJwtException ex){
        ApiResult<List<ErrorData>> apiResult = ApiResult.failResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiResult, HttpStatus.CONFLICT);
    }
}
