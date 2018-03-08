package bit.gd.pojo;

import java.util.Date;

public class GDResultSmo {
    private Integer id;

    private Integer parametersId;

    private String userNo;

    private String errorMat;

    private String errorWeightMat;

    private String maskPatternMat;

    private String printImageMat;

    private String sourcePatternMat;

    private String maskBinaryPng;

    private String printImagePng;

    private String sourcePatternPng;

    private String errorConvergencePng;

    private Date createTime;

    public GDResultSmo(Integer id, Integer parametersId, String userNo, String errorMat, String errorWeightMat, String maskPatternMat, String printImageMat, String sourcePatternMat, String maskBinaryPng, String printImagePng, String sourcePatternPng, String errorConvergencePng, Date createTime) {
        this.id = id;
        this.parametersId = parametersId;
        this.userNo = userNo;
        this.errorMat = errorMat;
        this.errorWeightMat = errorWeightMat;
        this.maskPatternMat = maskPatternMat;
        this.printImageMat = printImageMat;
        this.sourcePatternMat = sourcePatternMat;
        this.maskBinaryPng = maskBinaryPng;
        this.printImagePng = printImagePng;
        this.sourcePatternPng = sourcePatternPng;
        this.errorConvergencePng = errorConvergencePng;
        this.createTime = createTime;
    }

    public GDResultSmo() {
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

    public String getErrorWeightMat() {
        return errorWeightMat;
    }

    public void setErrorWeightMat(String errorWeightMat) {
        this.errorWeightMat = errorWeightMat == null ? null : errorWeightMat.trim();
    }

    public String getMaskPatternMat() {
        return maskPatternMat;
    }

    public void setMaskPatternMat(String maskPatternMat) {
        this.maskPatternMat = maskPatternMat == null ? null : maskPatternMat.trim();
    }

    public String getPrintImageMat() {
        return printImageMat;
    }

    public void setPrintImageMat(String printImageMat) {
        this.printImageMat = printImageMat == null ? null : printImageMat.trim();
    }

    public String getSourcePatternMat() {
        return sourcePatternMat;
    }

    public void setSourcePatternMat(String sourcePatternMat) {
        this.sourcePatternMat = sourcePatternMat == null ? null : sourcePatternMat.trim();
    }

    public String getMaskBinaryPng() {
        return maskBinaryPng;
    }

    public void setMaskBinaryPng(String maskBinaryPng) {
        this.maskBinaryPng = maskBinaryPng == null ? null : maskBinaryPng.trim();
    }

    public String getPrintImagePng() {
        return printImagePng;
    }

    public void setPrintImagePng(String printImagePng) {
        this.printImagePng = printImagePng == null ? null : printImagePng.trim();
    }

    public String getSourcePatternPng() {
        return sourcePatternPng;
    }

    public void setSourcePatternPng(String sourcePatternPng) {
        this.sourcePatternPng = sourcePatternPng == null ? null : sourcePatternPng.trim();
    }

    public String getErrorConvergencePng() {
        return errorConvergencePng;
    }

    public void setErrorConvergencePng(String errorConvergencePng) {
        this.errorConvergencePng = errorConvergencePng == null ? null : errorConvergencePng.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}