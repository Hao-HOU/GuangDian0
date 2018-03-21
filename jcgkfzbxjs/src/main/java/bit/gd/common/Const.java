package bit.gd.common;

/**
 * Created by Hao HOU on 2017/8/2.
 */
public class Const {
    public static final String CURRENT_USER_NAME = "currentUserName";
    public static final String CURRENT_USER_ROLES = "currentUserRoles";

    public static final String INITIAL_PASSWORD = "123456";
    public static final String USERNAME = "username";

    public static final String LAST_SECOND = " 23:59:59";

    public static final String UPLOAD_FILE_PATH = "upload";
    public static final String RESULT_PATH_SMO = "smo";
    public static final String RESULT_PATH_OPC = "opc";
    public static final String INTER_FILE_PATH = "inter";

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

    public interface SmoMatlabOutputFilename {
        String SMO_Error_Mat = "error.mat";
        String SMO_Iteration_Times_Mat = "Iteration_Times.mat";
        String SMO_Error_Convergence_Png = "error_convergence.png";
        String SMO_Error_Weight_Mat = "error_weight.mat";
        String SMO_Error_Convergence_Weight_Png = "error_convergence_weight.png";
        String SMO_Mask_Pattern_Png = "mask_pattern.png";
        String SMO_Mask_Pattern_Mat = "mask_pattern.mat";
        String SMO_Print_Image_Mat = "print_image.mat";
        String SMO_Print_Image_Png = "print_image.png";
        String SMO_Source_Pattern_Mat = "source_pattern.mat";
        String SMO_Source_Pattern_Png = "source_pattern.png";
    }

    public interface OpcMatlabOutputFilename {
        String OPC_Error_Mat = "error.mat";
        String OPC_Iteration_Times_Mat = "Iteration_Times.mat";
        String OPC_Error_Convergence_Png = "error_convergence.png";
        String OPC_Error_Weight_Mat = "error_weight.mat";
        String OPC_Error_Convergence_Weight_Png = "error_convergence_weight.png";
        String OPC_Mask_Pattern_Png = "mask_pattern.png";
        String OPC_Mask_Pattern_Mat = "mask_pattern.mat";
        String OPC_Print_Image_Mat = "print_image.mat";
        String OPC_Print_Image_Png = "print_image.png";
        String OPC_Source_Pattern_Mat = "source_pattern.mat";
        String OPC_Source_Pattern_Png = "source_pattern.png";
    }

    public interface Status {
        int ACTIVE = 0;//账号可用
        int FROZEN = 1;//账号不可用
    }

    public interface RunningState {
        int IDLE = 0;//该用户该模块暂无任务执行
        int RUNNING = 1;//该用户正在使用该模块
    }

}
