import bit.gd.common.Const;
import bit.gd.util.FTPUtil;
import bit.gd.util.FileMD5Util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by Hao HOU on 2018/3/11.
 */
public class AnotherMD5Test {
    public static void main(String args[]) {
        try {
            InputStream inputStream = FTPUtil.getFile(Const.UPLOAD_FILE_PATH, "2b5756ec-ca8a-4438-9e5c-d7e63b5592a4-pwo.mat");
            if (inputStream == null) {

            } else {
                System.out.println(FileMD5Util.getMD5Checksum(inputStream));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(getMD5Checksum("E:\\ztest\\FTPServer\\upload\\2b5756ec-ca8a-4438-9e5c-d7e63b5592a4-pwo.mat"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);          //<span style="color:rgb(51,51,51);font-family:arial;font-size:13px;line-height:20px;">将流类型字符串转换为String类型字符串</span>

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5"); //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
        int numRead;

        do {
            numRead = fis.read(buffer);    //从文件读到buffer，最多装满buffer
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);  //用读到的字节进行MD5的计算，第二个参数是偏移量
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
       StringBuilder sb = new StringBuilder();

        for (int i=0; i < b.length; i++) {
            sb.append(Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring(1));//加0x100是因为有的b[i]的十六进制只有1位
        }
        return sb.toString();
    }
}
