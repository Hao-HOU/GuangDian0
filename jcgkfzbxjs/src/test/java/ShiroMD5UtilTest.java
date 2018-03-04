import bit.gd.pojo.GDUser;
import bit.gd.util.ShiroMD5Util;
import org.junit.Test;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:46
 */
public class ShiroMD5UtilTest {
    @Test
    public void encode() {
        GDUser gdUser = new GDUser();
        gdUser.setUserNo("2120160998");
        gdUser.setPassword("123");
        System.out.println(ShiroMD5Util.shiroMD5Encode(gdUser.getUserNo(), gdUser.getPassword()));
    }
}
