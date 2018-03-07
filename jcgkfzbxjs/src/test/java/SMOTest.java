import bit.gd.service.IConnectMatlabService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Hao HOU on 2018/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SMOTest  {
    @Autowired
    IConnectMatlabService iConnectMatlabService;

    @Test
    public void smo() {
//        iConnectMatlabService.firstTrySMO();
    }
}
