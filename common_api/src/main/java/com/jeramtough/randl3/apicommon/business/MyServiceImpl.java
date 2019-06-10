package com.jeramtough.randl3.apicommon.business;

import com.jeramtough.jtcomponent.task.bean.no.TaskResult;
import com.jeramtough.randl3.apicommon.component.ApiFailedMessager;

/**
 * Created on 2019-04-09 15:17
 * by @author JeramTough
 */
public class MyServiceImpl {

    private final ApiFailedMessager apiFailedMessager;

    public MyServiceImpl(
            ApiFailedMessager apiFailedMessager) {
        this.apiFailedMessager = apiFailedMessager;
    }

    public void putErrorCode(TaskResult taskResult, int errorCode) {
        taskResult.putPayload("errorCode", errorCode);
        taskResult.setMessage(apiFailedMessager.getApiErrorMessage(errorCode));
    }

    public void putErrorCode(TaskResult taskResult, int errorCode, Object... holders) {
        taskResult.putPayload("errorCode", errorCode);
        taskResult.setMessage(apiFailedMessager.getApiErrorMessage(errorCode,
                holders));
    }
}
