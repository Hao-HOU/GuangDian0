package bit.gd.pojo;

import java.util.Date;

public class GDUser {
    private Integer id;

    private String userNo;

    private String password;

    private String name;

    private String phone;

    private Integer status;

    private String adminName;

    private Date crateTime;

    private Date updateTime;

    public GDUser(Integer id, String userNo, String password, String name, String phone, Integer status, String adminName, Date crateTime, Date updateTime) {
        this.id = id;
        this.userNo = userNo;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.adminName = adminName;
        this.crateTime = crateTime;
        this.updateTime = updateTime;
    }

    public GDUser() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}