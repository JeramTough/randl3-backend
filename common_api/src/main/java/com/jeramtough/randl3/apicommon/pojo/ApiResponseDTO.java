package com.jeramtough.randl3.apicommon.pojo;

import java.io.Serializable;

/**
 * Created on 2019-03-14 09:08
 * by @author JeramTough
 */
public class ApiResponseDTO implements Serializable {

    private Integer statusCode;

    private Object responseBody;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "ApiResponseDTO{" +
                "statusCode=" + statusCode +
                ", responseBody=" + responseBody.toString() +
                '}';
    }
}
