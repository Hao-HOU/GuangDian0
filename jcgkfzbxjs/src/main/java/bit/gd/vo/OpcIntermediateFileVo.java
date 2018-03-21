package bit.gd.vo;

import bit.gd.common.Const;
import bit.gd.util.PropertiesUtil;

import java.io.File;

/**
 * Created by Hao HOU on 2018/3/21.
 */
public class OpcIntermediateFileVo {
    private double error;
    private double errorWeight;
    private double iterationCount;
    private String ip;
    private String opcErrorConvergencePng;
    private String opcMaskPatternPng;
    private String opcPrintImagePng;
    private String opcSourcePatternPng;
    private String opcErrorConvergenceWeightPng;

    public OpcIntermediateFileVo(String userNo) {
        this.ip = PropertiesUtil.getProperty("tomcat.ip");
        this.opcErrorConvergencePng = userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Error_Convergence_Png;
        this.opcMaskPatternPng = userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Mask_Pattern_Png;
        this.opcPrintImagePng = userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Print_Image_Png;
        this.opcSourcePatternPng = userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Source_Pattern_Png;
        this.opcErrorConvergenceWeightPng = userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Error_Convergence_Weight_Png;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getErrorWeight() {
        return errorWeight;
    }

    public void setErrorWeight(double errorWeight) {
        this.errorWeight = errorWeight;
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

    public String getOpcErrorConvergencePng() {
        return opcErrorConvergencePng;
    }

    public String getOpcMaskPatternPng() {
        return opcMaskPatternPng;
    }

    public String getOpcPrintImagePng() {
        return opcPrintImagePng;
    }

    public String getOpcSourcePatternPng() {
        return opcSourcePatternPng;
    }

    public String getOpcErrorConvergenceWeightPng() {
        return opcErrorConvergenceWeightPng;
    }
}
