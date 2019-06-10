package com.jeramtough.randl3.apicommon.component;

import com.jeramtough.jtcomponent.task.bean.TaskResult;
import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.jtlog.with.WithLogger;
import com.jeramtough.randl3.apicommon.pojo.ApiResponseDTO;

/**
 * Created on 2019-03-14 09:47
 * by @author JeramTough
 */
public class ApiResponseDtoFactory implements WithLogger {

    private static final int SUCCESSFUL_STATUS_CODE = 666;

    public ApiResponseDtoFactory() {
    }

    public ApiResponseDTO getSuccessfulApiResponseDTO(Object responseBody) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setResponseBody(responseBody);
        apiResponseDTO.setStatusCode(SUCCESSFUL_STATUS_CODE);
        return apiResponseDTO;
    }

    public ApiResponseDTO getSuccessfulApiResponseDTO() {
        return getSuccessfulApiResponseDTO(null);
    }

    public ApiResponseDTO getFailedApiResponseDTO(TaskResponse taskResponse) {
        return getFailedApiResponseDTO(taskResponse.getTaskResult());
    }

    public ApiResponseDTO getFailedApiResponseDTO(TaskResult taskResult) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        int errorCode = taskResult.getIntegerPayload("errorCode");
        String errorMessage = taskResult.getMessage();
        String code = String.valueOf(errorCode);

        apiResponseDTO.setStatusCode(errorCode);
        apiResponseDTO.setResponseBody(errorMessage);
        return apiResponseDTO;
    }
}
