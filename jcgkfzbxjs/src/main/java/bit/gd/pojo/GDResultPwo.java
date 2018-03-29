package bit.gd.pojo;

import java.util.Date;

public class GDResultPwo {
    private Integer id;

    private Integer parametersId;

    private String userNo;

    private String errorMat;

    private String errorConvergencePupilPng;

    private String maskPatternMat;

    private String maskPatternPng;

    private String printImagePng;

    private String sourcePatternMat;

    private String sourcePatternPng;

    private String targetPatternMat;

    private String targetPatternPng;

    private String theitaPupilPng;

    private Date createTime;

    public GDResultPwo(Integer id, Integer parametersId, String userNo, String errorMat, String errorConvergencePupilPng, String maskPatternMat, String maskPatternPng, String printImagePng, String sourcePatternMat, String sourcePatternPng, String targetPatternMat, String targetPatternPng, String theitaPupilPng, Date createTime) {
        this.id = id;
        this.parametersId = parametersId;
        this.userNo = userNo;
        this.errorMat = errorMat;
        this.errorConvergencePupilPng = errorConvergencePupilPng;
        this.maskPatternMat = maskPatternMat;
        this.maskPatternPng = maskPatternPng;
        this.printImagePng = printImagePng;
        this.sourcePatternMat = sourcePatternMat;
        this.sourcePatternPng = sourcePatternPng;
        this.targetPatternMat = targetPatternMat;
        this.targetPatternPng = targetPatternPng;
        this.theitaPupilPng = theitaPupilPng;
        this.createTime = createTime;
    }

    public GDResultPwo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParametersId() {
        return parametersId;
    }

    public void setParametersId(Integer parametersId) {
        this.parametersId = parametersId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getErrorMat() {
        return errorMat;
    }

    public void setErrorMat(String errorMat) {
        this.errorMat = errorMat == null ? null : errorMat.trim();
    }

    public String getErrorConvergencePupilPng() {
        return errorConvergencePupilPng;
    }

    public void setErrorConvergencePupilPng(String errorConvergencePupilPng) {
        this.errorConvergencePupilPng = errorConvergencePupilPng == null ? null : errorConvergencePupilPng.trim();
    }

    public String getMaskPatternMat() {
        return maskPatternMat;
    }

    public void setMaskPatternMat(String maskPatternMat) {
        this.maskPatternMat = maskPatternMat == null ? null : maskPatternMat.trim();
    }

    public String getMaskPatternPng() {
        return maskPatternPng;
    }

    public void setMaskPatternPng(String maskPatternPng) {
        this.maskPatternPng = maskPatternPng == null ? null : maskPatternPng.trim();
    }

    public String getPrintImagePng() {
        return printImagePng;
    }

    public void setPrintImagePng(String printImagePng) {
        this.printImagePng = printImagePng == null ? null : printImagePng.trim();
    }

    public String getSourcePatternMat() {
        return sourcePatternMat;
    }

    public void setSourcePatternMat(String sourcePatternMat) {
        this.sourcePatternMat = sourcePatternMat == null ? null : sourcePatternMat.trim();
    }

    public String getSourcePatternPng() {
        return sourcePatternPng;
    }

    public void setSourcePatternPng(String sourcePatternPng) {
        this.sourcePatternPng = sourcePatternPng == null ? null : sourcePatternPng.trim();
    }

    public String getTargetPatternMat() {
        return targetPatternMat;
    }

    public void setTargetPatternMat(String targetPatternMat) {
        this.targetPatternMat = targetPatternMat == null ? null : targetPatternMat.trim();
    }

    public String getTargetPatternPng() {
        return targetPatternPng;
    }

    public void setTargetPatternPng(String targetPatternPng) {
        this.targetPatternPng = targetPatternPng == null ? null : targetPatternPng.trim();
    }

    public String getTheitaPupilPng() {
        return theitaPupilPng;
    }

    public void setTheitaPupilPng(String theitaPupilPng) {
        this.theitaPupilPng = theitaPupilPng == null ? null : theitaPupilPng.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}