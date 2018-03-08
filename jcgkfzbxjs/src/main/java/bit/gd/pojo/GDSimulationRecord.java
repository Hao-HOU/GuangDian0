package bit.gd.pojo;

import java.util.Date;

public class GDSimulationRecord {
    private Integer id;

    private String userNo;

    private String moduleName;

    private Integer parametersId;

    private Integer resultId;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public GDSimulationRecord(Integer id, String userNo, String moduleName, Integer parametersId, Integer resultId, Date startTime, Date endTime, Date createTime) {
        this.id = id;
        this.userNo = userNo;
        this.moduleName = moduleName;
        this.parametersId = parametersId;
        this.resultId = resultId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
    }

    public GDSimulationRecord() {
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Integer getParametersId() {
        return parametersId;
    }

    public void setParametersId(Integer parametersId) {
        this.parametersId = parametersId;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}