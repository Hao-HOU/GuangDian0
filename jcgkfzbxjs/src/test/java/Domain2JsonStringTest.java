import bit.gd.pojo.GDParameterSmo;
import bit.gd.request.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/1 00:26
 */
public class Domain2JsonStringTest {
    @Test
    public void user2Json() {
        User user = new User();
        user.setUsername("houhao");
        user.setPassword("aaaa");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void gDParameterSmo2Json() {
        GDParameterSmo gdParameterSmo = new GDParameterSmo();
        gdParameterSmo.setMaskDimension(201.0);
        gdParameterSmo.setPixelSize(11.0);
        gdParameterSmo.setReflect(0.8);
        gdParameterSmo.setAbsorb(0.0707);
        gdParameterSmo.setShadowNear(1.424);
        gdParameterSmo.setShadowFar(1.835);

        gdParameterSmo.setWavelength(13.5);
        gdParameterSmo.setSigmaIn(0.24);
        gdParameterSmo.setSigmaOut(0.84);
        gdParameterSmo.setTis(0.169);
        gdParameterSmo.setNa(0.25);
        gdParameterSmo.setRatio(4.0);

        gdParameterSmo.setStepSource(0.03);
        gdParameterSmo.setOmegaSourceQua(0.001);
        gdParameterSmo.setStepMaskMain(0.1);
        gdParameterSmo.setStepMaskSraf(0.1);
        gdParameterSmo.setOmegaMaskQua(0.0005);
        gdParameterSmo.setMaxloopSmo(1.0);
        gdParameterSmo.setThreshold(100.0);
        gdParameterSmo.setTr(0.25);
        gdParameterSmo.setaSource(25.0);

        gdParameterSmo.setCoreNum(4.0);
        gdParameterSmo.setInputMask("E:/ztest/target4");

        System.out.println(JSON.toJSONString(gdParameterSmo));
    }
}
