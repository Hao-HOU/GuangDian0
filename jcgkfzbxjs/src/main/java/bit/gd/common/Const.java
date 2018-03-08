package bit.gd.common;

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
        String ROLE_SMO = "smo";
        String ROLE_OPC = "opc";
        String ROLE_SMPWO = "smpwo";
        String ROLE_PDOD = "pdod";
    }

    public interface Module {
        String MODULE_SMO = "smo";
        String MODULE_OPC = "opc";
        String MODULE_SMPWO = "smpwo";
        String MODULE_PDOD = "pdod";
    }

    public interface Status {
        int ACTIVE = 0;//账号可用
        int FROZEN = 1;//账号不可用
    }

}
