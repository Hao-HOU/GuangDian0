package bit.gd.vo;

import java.util.List;
import java.util.Set;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 14:44
 */
public class UserVo {
    private int id;
    private String userNo;
    private String name;
    private String phone;
    private Set<String> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
