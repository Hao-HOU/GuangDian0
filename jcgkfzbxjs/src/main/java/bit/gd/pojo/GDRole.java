package bit.gd.pojo;

import java.util.Date;

public class GDRole {
    private Integer id;

    private String roleName;

    private String adminName;

    private Date createTime;

    private Date updateTime;

    public GDRole(Integer id, String roleName, String adminName, Date createTime, Date updateTime) {
        this.id = id;
        this.roleName = roleName;
        this.adminName = adminName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GDRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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