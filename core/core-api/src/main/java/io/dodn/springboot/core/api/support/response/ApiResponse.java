package io.dodn.springboot.core.api.support.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.dodn.springboot.core.api.support.error.ErrorMessage;
import io.dodn.springboot.core.api.support.error.ErrorType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<S> {

    private final ResultType result;

    private final S data;

    private final ErrorMessage error;

    private ApiResponse(ResultType result, S data, ErrorMessage error) {
        this.result = result;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResponse<S> success(S data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(ErrorType error) {
        return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error));
    }

    public static ApiResponse<?> error(ErrorType error, Object errorData) {
        return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error, errorData));
    }

    public static ApiResponse<?> error(ErrorType error, String message) {
        return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error, message));
    }

    public ResultType getResult() {
        return result;
    }

    public Object getData() {
        return data;
    }

    public ErrorMessage getError() {
        return error;
    }

}
