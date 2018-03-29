package bit.gd.pojo;

import java.util.Date;

public class GDParameterPwo {
    private Integer id;

    private String userNo;

    private Double wavelength;

    private Double na;

    private Double ratio;

    private Double polarization;

    private Double refractiveIndex;

    private Double pixel;

    private Double stepPupil;

    private Double omegaPupil;

    private Double zterm;

    private Double resistSlope;

    private Double threshold;

    private Double dimension;

    private Double maxloop;

    private Double coreNum;

    private String inputData;

    private String inputDataHash;

    private Date createTime;

    private Date updateTime;

    public GDParameterPwo(Integer id, String userNo, Double wavelength, Double na, Double ratio, Double polarization, Double refractiveIndex, Double pixel, Double stepPupil, Double omegaPupil, Double zterm, Double resistSlope, Double threshold, Double dimension, Double maxloop, Double coreNum, String inputData, String inputDataHash, Date createTime, Date updateTime) {
        this.id = id;
        this.userNo = userNo;
        this.wavelength = wavelength;
        this.na = na;
        this.ratio = ratio;
        this.polarization = polarization;
        this.refractiveIndex = refractiveIndex;
        this.pixel = pixel;
        this.stepPupil = stepPupil;
        this.omegaPupil = omegaPupil;
        this.zterm = zterm;
        this.resistSlope = resistSlope;
        this.threshold = threshold;
        this.dimension = dimension;
        this.maxloop = maxloop;
        this.coreNum = coreNum;
        this.inputData = inputData;
        this.inputDataHash = inputDataHash;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GDParameterPwo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public Double getWavelength() {
        return wavelength;
    }

    public void setWavelength(Double wavelength) {
        this.wavelength = wavelength;
    }

    public Double getNa() {
        return na;
    }

    public void setNa(Double na) {
        this.na = na;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getPolarization() {
        return polarization;
    }

    public void setPolarization(Double polarization) {
        this.polarization = polarization;
    }

    public Double getRefractiveIndex() {
        return refractiveIndex;
    }

    public void setRefractiveIndex(Double refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
    }

    public Double getPixel() {
        return pixel;
    }

    public void setPixel(Double pixel) {
        this.pixel = pixel;
    }

    public Double getStepPupil() {
        return stepPupil;
    }

    public void setStepPupil(Double stepPupil) {
        this.stepPupil = stepPupil;
    }

    public Double getOmegaPupil() {
        return omegaPupil;
    }

    public void setOmegaPupil(Double omegaPupil) {
        this.omegaPupil = omegaPupil;
    }

    public Double getZterm() {
        return zterm;
    }

    public void setZterm(Double zterm) {
        this.zterm = zterm;
    }

    public Double getResistSlope() {
        return resistSlope;
    }

    public void setResistSlope(Double resistSlope) {
        this.resistSlope = resistSlope;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Double getDimension() {
        return dimension;
    }

    public void setDimension(Double dimension) {
        this.dimension = dimension;
    }

    public Double getMaxloop() {
        return maxloop;
    }

    public void setMaxloop(Double maxloop) {
        this.maxloop = maxloop;
    }

    public Double getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(Double coreNum) {
        this.coreNum = coreNum;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData == null ? null : inputData.trim();
    }

    public String getInputDataHash() {
        return inputDataHash;
    }

    public void setInputDataHash(String inputDataHash) {
        this.inputDataHash = inputDataHash == null ? null : inputDataHash.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}