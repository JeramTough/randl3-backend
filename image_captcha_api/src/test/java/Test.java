import com.jeramtough.jtlog.facade.L;
import com.jeramtough.randl3.apicommon.util.ConfigurationUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

/**
 * Created on 2019-03-28 16:04
 * by @author JeramTough
 */
public class Test {

    @org.junit.Test
    public void test1() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
        L.debug(pool.getResource().ping());
        Jedis jedis=pool.getResource();
        pool.close();
        L.debug(jedis.ping());
        jedis.close();
    }

    @org.junit.Test
    public void test2() {
        String a = "127.0.0.1:8098,127.9.0.1:8989";
        String[][] b = ConfigurationUtil.getRedisHosts(a);
        for (int i=0;i<b.length;i++){
            L.debug(Arrays.toString(b[i]));
        }
    }

    @org.junit.Test
    public void test3(){

    }

}
