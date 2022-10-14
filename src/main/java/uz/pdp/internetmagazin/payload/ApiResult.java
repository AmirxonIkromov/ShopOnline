package uz.pdp.internetmagazin.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResult<E> {

    private boolean success;

    private String message;

    private E data;

    private List<ErrorData> errors;

    private ApiResult(boolean success, String message, E data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    private ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private ApiResult(boolean success, E data) {
        this.success = success;
        this.data = data;
    }

    private ApiResult(boolean success) {
        this.success = success;
    }

    private ApiResult(E data) {
        this.data = data;
    }

    public static <T> ApiResult<T> successResponce() {
        return new ApiResult<>(true);
    }
    public static <T> ApiResult<T> successResponce(String massage) {
        return new ApiResult<>(true,massage);
    }
    public static <T> ApiResult<T> successResponce(String massage, T data) {
        return new ApiResult<>(true,massage,data);
    }
    public static <T> ApiResult<T> successResponce(T data) {
        return new ApiResult<>(true,data);
    }

    public static ApiResult<List<ErrorData>> failResponse(String msg, Integer code){
        ErrorData errorData = new ErrorData(msg,code);
        return new ApiResult<>(List.of(errorData));
    }

    public static ApiResult<List<ErrorData>> failResponse(List<ErrorData> errors){
        return new ApiResult<>(errors);
    }




}
