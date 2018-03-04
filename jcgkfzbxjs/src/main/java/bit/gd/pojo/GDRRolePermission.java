package bit.gd.pojo;

import java.util.Date;

public class GDRRolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private String adminName;

    private Date createTime;

    private Integer updateTime;

    public GDRRolePermission(Integer id, Integer roleId, Integer permissionId, String adminName, Date createTime, Integer updateTime) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.adminName = adminName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GDRRolePermission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
}