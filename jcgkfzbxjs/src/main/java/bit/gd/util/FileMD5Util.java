package bit.gd.util;

/**
 * Created by Hao HOU on 2018/3/10.
 */
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Md5校验工具类
 */
public class FileMD5Util {

    public static byte[] createChecksum(InputStream is) throws Exception {
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5"); //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
        int numRead;

        do {
            numRead = is.read(buffer);    //从文件读到buffer，最多装满buffer
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);  //用读到的字节进行MD5的计算，第二个参数是偏移量
            }
        } while (numRead != -1);

        is.close();
        return complete.digest();
    }

    public static String getMD5Checksum(InputStream inputStream) throws Exception {
        byte[] b = createChecksum(inputStream);
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < b.length; i++) {
            sb.append(Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring(1));//加0x100是因为有的b[i]的十六进制只有1位
        }
        return sb.toString();
    }
}
