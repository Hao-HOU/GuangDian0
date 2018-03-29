package bit.gd.util;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLDouble;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Hao HOU on 2018/3/11.
 */
public class JMatIOUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JMatIOUtil.class);

    public static double getErrorMatValue(String matFilePath) {
        MatFileReader reader = null;
        try {
            reader = new MatFileReader(matFilePath);
        } catch (IOException e) {
            LOGGER.info("读取.mat文件中的值失败");
        }
        MLArray mlArray = reader.getMLArray("error");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());

        return matrix[0][0];
    }

    public static double getIterationCount(String matFilePath) {
        MatFileReader reader = null;
        try {
            reader = new MatFileReader(matFilePath);
        } catch (IOException e) {
            LOGGER.info("读取.mat文件中的值失败");
        }
        MLArray mlArray = reader.getMLArray("count");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());

        return matrix[0][0];
    }

    public static double getErrorWeight(String matFilePath) {
        MatFileReader reader = null;
        try {
            reader = new MatFileReader(matFilePath);
        } catch (IOException e) {
            LOGGER.info("读取.mat文件中的值失败");
        }
        MLArray mlArray = reader.getMLArray("error_weight");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());

        return matrix[0][0];
    }

    public static double getPwoErrorMatValue(String matFilePath) {
        MatFileReader reader = null;
        try {
            reader = new MatFileReader(matFilePath);
        } catch (IOException e) {
            LOGGER.info("读取.mat文件中的值失败");
        }
        MLArray mlArray = reader.getMLArray("error_pupil");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());

        return matrix[0][0];
    }

    public static double getPwoIterationCount(String matFilePath) {
        MatFileReader reader = null;
        try {
            reader = new MatFileReader(matFilePath);
        } catch (IOException e) {
            LOGGER.info("读取.mat文件中的值失败");
        }
        MLArray mlArray = reader.getMLArray("count_pupil");
        MLDouble mlDouble = (MLDouble) mlArray;
        double[][] matrix = (mlDouble.getArray());

        return matrix[0][0];
    }
}
