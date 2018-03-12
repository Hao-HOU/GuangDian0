package bit.gd.vo;

import bit.gd.common.Const;
import bit.gd.util.PropertiesUtil;

/**
 * Created by Hao HOU on 2018/3/12.
 */
public class SmoIntermediateFileVo {
    private String ip;
    //private String smoErrorConvergencePng;
    private String smoMaskBinaryPng;
    private String smoPrintImagePng;
    private String smoSourcePatternPng;

    public SmoIntermediateFileVo() {
        this.ip = PropertiesUtil.getProperty("tomcat.ip");
        //this.smoErrorConvergencePng = Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Png;
        this.smoMaskBinaryPng = Const.SmoMatlabOutputFilename.SMO_Mask_Binary_Png;
        this.smoPrintImagePng = Const.SmoMatlabOutputFilename.SMO_Print_Image_Png;
        this.smoSourcePatternPng = Const.SmoMatlabOutputFilename.SMO_Source_Pattern_Png;
    }

    public String getIp() {
        return ip;
    }

    //    public String getSmoErrorConvergencePng() {
//        return smoErrorConvergencePng;
//    }

    public String getSmoMaskBinaryPng() {
        return smoMaskBinaryPng;
    }

    public String getSmoPrintImagePng() {
        return smoPrintImagePng;
    }

    public String getSmoSourcePatternPng() {
        return smoSourcePatternPng;
    }
}
