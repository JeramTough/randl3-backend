package com.jeramtough.randl3.svcapi.action.controller;

import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.randl3.apicommon.action.controller.MyAction;
import com.jeramtough.randl3.apicommon.pojo.ApiResponseDTO;
import com.jeramtough.randl3.apicommon.component.ApiResponseDtoFactory;
import com.jeramtough.randl3.svcapi.business.SvcService;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019/4/11 14:47
 * by @author JeramTough
 */
@Controller
public class SvcController extends MyAction {

    private SvcService svcService;

    @Autowired
    public SvcController(
            ApiResponseDtoFactory apiResponseDtoFactory,
            SvcService svcService) {
        super(apiResponseDtoFactory);
        this.svcService = svcService;
    }

    @RequestMapping(value = "randl3/send/verificationCode", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponseDTO sendVerificationCode(
            @RequestBody SendVerificationCodeApiForm sendVerificationCodeApiForm) {
        TaskResponse taskResponse = svcService.sendVerificationCode(
                sendVerificationCodeApiForm);
        if (taskResponse.getTaskResult().isSuccessful()) {
            return getApiResponseDtoFactory().getSuccessfulApiResponseDTO("发送成功！");
        }
        else {
            return getApiResponseDtoFactory().getFailedApiResponseDTO(taskResponse);
        }
    }

    @RequestMapping(value = "randl3/verify/verificationCode", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponseDTO verifyVerificationCode() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute( "someKey", "aValue" );
        if ( !currentUser.isAuthenticated() ) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);
            currentUser.login(token);
        }
        return null;
    }


}
