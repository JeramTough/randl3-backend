package com.jeramtough.randl3.apicommon.action.controller;

import com.jeramtough.randl3.apicommon.pojo.ApiResponseDTO;
import com.jeramtough.randl3.apicommon.component.ApiResponseDtoFactory;
import com.jeramtough.jtcomponent.task.response.TaskResponse;

/**
 * Created on 2019-04-09 15:12
 * by @author JeramTough
 */
public class MyAction {

    private ApiResponseDtoFactory apiResponseDtoFactory;

    public MyAction(
            ApiResponseDtoFactory apiResponseDtoFactory) {
        this.apiResponseDtoFactory = apiResponseDtoFactory;
    }

    public ApiResponseDtoFactory getApiResponseDtoFactory() {
        return apiResponseDtoFactory;
    }



}
