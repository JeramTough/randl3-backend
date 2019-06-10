import com.jeramtough.jtlog.facade.L;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

/**
 * Created on 2019-04-10 15:50
 * by @author JeramTough
 */
public class Test {

    @org.junit.Test
    public void testInputSteam() {
        try {
            int count = getClass().getClassLoader().getResourceAsStream("a").available();
            L.debug(count);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testForm() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        SendVerificationCodeApiForm sendVerificationCodeApiForm =
                new SendVerificationCodeApiForm();
        sendVerificationCodeApiForm.setEmailAddress("121q.com");
        sendVerificationCodeApiForm.setPhoneNumber("2221");

        Set<ConstraintViolation<SendVerificationCodeApiForm>> constraintViolations =
                validator.validate(sendVerificationCodeApiForm);
        L.debug(constraintViolations.size());
        for (ConstraintViolation<SendVerificationCodeApiForm> violation : constraintViolations) {
            L.debug(violation.getMessage());
        }
        //上次发送验证码的类型，上次发送验证码的时间
    }

}
