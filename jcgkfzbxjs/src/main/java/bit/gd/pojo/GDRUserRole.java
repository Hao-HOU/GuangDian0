package bit.gd.pojo;

import java.util.Date;

public class GDRUserRole {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private String adminName;

    private Date createTime;

    private Date updateTime;

    public GDRUserRole(Integer id, Integer userId, Integer roleId, String adminName, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.adminName = adminName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GDRUserRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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