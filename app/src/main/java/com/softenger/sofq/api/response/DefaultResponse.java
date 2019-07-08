package com.softenger.sofq.api.response;

import java.util.Arrays;

public class DefaultResponse {

    private String message;
    private Integer responseCode;
    private Integer success;
    private Integer failure;

    private Object[] result;

    public Object[] getResult() {
        return result;
    }

    public void setResult(Object[] result) {
        this.result = result;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "message='" + message + '\'' +
                ", responseCode=" + responseCode +
                ", success=" + success +
                ", failure=" + failure +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
