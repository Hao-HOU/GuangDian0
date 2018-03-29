package bit.gd.vo;

import bit.gd.common.Const;
import bit.gd.util.PropertiesUtil;

import java.io.File;

/**
 * Created by Hao HOU on 2018/3/29.
 */
public class PwoIntermediateFileVo {
    private double error;
    private double iterationCount;
    private int progress;
    private String ip;
    private String pwoMaskPatternPng;
    private String pwoPrintImagePng;
    private String pwoSourcePatternPng;
    private String pwoTargetPatternPng;
    private String pwoTheitaPupilPng;

    public PwoIntermediateFileVo(String userNo) {
        this.ip = PropertiesUtil.getProperty("tomcat.ip");
        this.pwoMaskPatternPng = userNo + File.separator + Const.PwoMatlabOutputFilename.PWO_Mask_Pattern_Png;
        this.pwoPrintImagePng =  userNo + File.separator + Const.PwoMatlabOutputFilename.PWO_Print_Image_Png;
        this.pwoSourcePatternPng =  userNo + File.separator + Const.PwoMatlabOutputFilename.PWO_Source_Pattern_Png;
        this.pwoTargetPatternPng =  userNo + File.separator + Const.PwoMatlabOutputFilename.PWO_Target_Pattern_Png;
        this.pwoTheitaPupilPng =  userNo + File.separator + Const.PwoMatlabOutputFilename.PWO_Theita_Pupil_Png;
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getIp() {
        return ip;
    }

    public String getPwoMaskPatternPng() {
        return pwoMaskPatternPng;
    }

    public String getPwoPrintImagePng() {
        return pwoPrintImagePng;
    }

    public String getPwoSourcePatternPng() {
        return pwoSourcePatternPng;
    }

    public String getPwoTargetPatternPng() {
        return pwoTargetPatternPng;
    }

    public String getPwoTheitaPupilPng() {
        return pwoTheitaPupilPng;
    }
}
