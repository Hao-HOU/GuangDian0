package bit.gd.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Hao HOU on 2017/8/2.
 */
public class Const {
    public static final String CURRENT_USER = "currentUSer";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role {
        int ROLE_USER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public interface Status {
        int ACTIVE = 0;//可用
        int NOT_ACTIVE = 1;//不可用
    }

}
