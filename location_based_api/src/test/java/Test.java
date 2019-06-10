import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtutil.java.file.Directory;
import com.jeramtough.randl3.lbapi.component.qqwry.LocationDat;
import com.jeramtough.randl3.lbapi.component.qqwry.bean.Location;

import java.io.File;

/**
 * Created on 2019-03-09 11:08
 * by @author JeramTough
 */
public class Test {


    @org.junit.Test
    public void testLocation1() {
        LocationDat locationDat = null;
        Directory directory = new Directory(".");
        for (File file : directory.listFiles()) {
            if (file.getName().contains("qqwry")) {
                locationDat = new LocationDat(file, false);
                break;
            }
        }
        Location loc = locationDat.fetchIPLocation("192.168.1.1");
        System.out.println(loc.toString());
    }
}
