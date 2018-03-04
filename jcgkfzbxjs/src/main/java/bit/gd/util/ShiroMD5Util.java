package bit.gd.util;

import bit.gd.pojo.GDUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:25
 */
public class ShiroMD5Util {
    public static String shiroMD5Encode(GDUser gdUser) {

        String hashAlgorithm = "MD5";
        Object credentials = gdUser.getPassword();
        ByteSource salt = ByteSource.Util.bytes(gdUser.getUserNo());
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithm, credentials, salt, hashIterations);

        return simpleHash.toString();
    }
}
