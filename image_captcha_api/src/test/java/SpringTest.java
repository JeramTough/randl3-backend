import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.randl3.captchaapi.ImageCaptchaApiBoot;
import com.jeramtough.randl3.captchaapi.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageCaptchaApiBoot.class)
public class SpringTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        L.debug(redisTemplate.hashCode());

        ValueOperations<String, String> valueOperations =
                redisTemplate.opsForValue();
        L.debug(valueOperations == null);



        valueOperations.set("name3", "fdsgdsfg");
        String name = valueOperations.get("name3");
        L.debug(name);
    }

}
