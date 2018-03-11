import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLDouble;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Hao HOU on 2018/3/10.
 */
public class JMatIOTest {
    @Test
    public void jMatIOTest() throws IOException {
        MatFileReader reader = new MatFileReader("E:\\项目\\光电三期\\20180304syy_示例程序\\20180304syy改_EUV_SMO_Par_Test\\error.mat");
        MLArray mlArray = reader.getMLArray("error");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());
        System.out.println(matrix[0][0]);
    }
}
