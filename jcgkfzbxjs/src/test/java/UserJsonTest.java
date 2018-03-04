import bit.gd.request.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/1 00:26
 */
public class UserJsonTest {
    @Test
    public void user2Json() {
        User user = new User();
        user.setUsername("houhao");
        user.setPassword("aaaa");
        System.out.println(JSON.toJSONString(user));
    }
}
