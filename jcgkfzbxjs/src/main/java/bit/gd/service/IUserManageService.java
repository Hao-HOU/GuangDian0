package bit.gd.service;

import bit.gd.pojo.GDUser;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:37
 */
public interface IUserManageService {
    int addUser(GDUser gdUser);
    GDUser getCurrentUserInfo(String userNo);
}
