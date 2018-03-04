package bit.gd.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Hao HOU on 2017/8/2.
 */
public class Const {
    public static final String CURRENT_USER_NAME = "currentUserName";
    public static final String CURRENT_USER_ROLES = "currentUserRoles";

    public static final String INITIAL_PASSWORD = "123456";
    public static final String USERNAME = "username";

    public interface Role {
        String ROLE_USER = "student";//普通用户
        String ROLE_ADMIN = "admin";//管理员
    }

    public interface Status {
        int ACTIVE = 0;//账号可用
        int FROZEN = 1;//账号不可用
    }

}
