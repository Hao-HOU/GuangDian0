package bit.gd.request;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/1 00:24
 */
public class User {
    private String username;//账号，学号或者工号
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
