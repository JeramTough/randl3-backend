package com.jeramtough.randl3.captchaapi.action.controller;

import com.jeramtough.jtcomponent.task.bean.TaskResult;
import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.randl3.apicommon.action.controller.MyAction;
import com.jeramtough.randl3.apicommon.pojo.ApiResponseDTO;
import com.jeramtough.randl3.apicommon.component.ApiResponseDtoFactory;
import com.jeramtough.randl3.captchaapi.business.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2019-03-13 16:37
 * by @author JeramTough
 */
@Controller
public class ImageCaptchaController extends MyAction {

    private final CaptchaService captchaService;

    @Autowired
    public ImageCaptchaController(
            ApiResponseDtoFactory apiResponseDtoFactory,
            CaptchaService captchaService) {
        super(apiResponseDtoFactory);
        this.captchaService = captchaService;
    }

    @RequestMapping(value = "randl3/get/captcha", method = RequestMethod.GET)
    public void getImageCaptcha(HttpServletResponse resp, @RequestParam(value = "random",
            required = true) String random) {

        resp.setContentType("image/jpeg");
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        resp.setDateHeader("Last-Modified", time);
        resp.setDateHeader("Date", time);
        resp.setDateHeader("Expires", time);

        TaskResult taskResult = captchaService.getCaptcha().getTaskResult();

        if (taskResult.isSuccessful()) {
            //get the bytes of image for captcha
            byte[] imageBytes = taskResult.getByteArrayPayload(
                    "imageBytes");
            try {
                resp.getOutputStream().write(imageBytes);
                resp.getOutputStream().flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            resp.setStatus(500);
        }

    }

    @RequestMapping(value = "randl3/verify/captcha", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponseDTO verifyImageCaptcha(@RequestParam(value = "code",
            required = true) String code) {
        TaskResponse taskResponse = captchaService.verifyCaptcha(code);
        if (taskResponse.getTaskResult().isSuccessful()) {
            return getApiResponseDtoFactory().getSuccessfulApiResponseDTO("验证成功！");
        }
        else{
            return getApiResponseDtoFactory().getFailedApiResponseDTO(taskResponse);
        }
    }

}
