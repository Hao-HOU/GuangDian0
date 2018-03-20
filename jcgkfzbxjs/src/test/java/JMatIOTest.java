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
        MatFileReader reader = new MatFileReader("E:\\ztest\\GDOutput\\Iteration_Times.mat");
        MLArray mlArray = reader.getMLArray("count");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());
        System.out.println(matrix[0][0]);
    }
}
