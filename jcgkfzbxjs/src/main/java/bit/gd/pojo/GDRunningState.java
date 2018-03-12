package bit.gd.pojo;

import java.util.Date;

public class GDRunningState {
    private Integer id;

    private String userNo;

    private String moduleName;

    private Integer runningStatus;

    private Integer performedTasks;

    private String adminName;

    private Date createTime;

    private Date updateTime;

    public GDRunningState(Integer id, String userNo, String moduleName, Integer runningStatus, Integer performedTasks, String adminName, Date createTime, Date updateTime) {
        this.id = id;
        this.userNo = userNo;
        this.moduleName = moduleName;
        this.runningStatus = runningStatus;
        this.performedTasks = performedTasks;
        this.adminName = adminName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GDRunningState() {
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

    public Integer getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(Integer runningStatus) {
        this.runningStatus = runningStatus;
    }

    public Integer getPerformedTasks() {
        return performedTasks;
    }

    public void setPerformedTasks(Integer performedTasks) {
        this.performedTasks = performedTasks;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
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