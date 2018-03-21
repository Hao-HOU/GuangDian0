package bit.gd.vo;

import bit.gd.common.Const;
import bit.gd.util.PropertiesUtil;

import java.io.File;

/**
 * Created by Hao HOU on 2018/3/12.
 */
public class SmoIntermediateFileVo {
    private double error;
    private double iterationCount;
    private String ip;
    private String smoErrorConvergencePng;
    private String smoMaskPatternPng;
    private String smoPrintImagePng;
    private String smoSourcePatternPng;
    private String smoErrorConvergenceWeightPng;

    public SmoIntermediateFileVo(String userNo) {
        this.ip = PropertiesUtil.getProperty("tomcat.ip");
        this.smoErrorConvergencePng = userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Png;
        this.smoMaskPatternPng = userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Mask_Pattern_Png;
        this.smoPrintImagePng = userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Print_Image_Png;
        this.smoSourcePatternPng = userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Source_Pattern_Png;
        this.smoErrorConvergenceWeightPng = userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Weight_Png;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(double iterationCount) {
        this.iterationCount = iterationCount;
    }

    public String getIp() {
        return ip;
    }

        public String getSmoErrorConvergencePng() {
        return smoErrorConvergencePng;
    }

    public String getSmoMaskPatternPng() {
        return smoMaskPatternPng;
    }

    public String getSmoPrintImagePng() {
        return smoPrintImagePng;
    }

    public String getSmoSourcePatternPng() {
        return smoSourcePatternPng;
    }

    public String getSmoErrorConvergenceWeightPng() {
        return smoErrorConvergenceWeightPng;
    }
}
