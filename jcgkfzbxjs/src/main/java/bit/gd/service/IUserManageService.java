package bit.gd.service;

import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDRole;
import bit.gd.pojo.GDUser;
import bit.gd.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:37
 */
public interface IUserManageService {
    int addUser(GDUser gdUser);

    UserVo getCurrentUserInfo(String userNo);

    String getCurrentUserPassword(String userNo);

    Set<String> getAllRolesName();

    List<GDRole> getAllRoles();

    int modifyThePassword (String userNo, String newPassword);

    ServerResponse<PageInfo> getAllActiveUsers(int pageNum, int pageSize);

    Set<String> getUserRoles(int userId);

    ServerResponse<PageInfo> getAllFrozenUsers(int pageNum, int pageSize);

    int modifyUserInfo(String userNo, String newName, String newPhone);

    int freezeTheUser(String userNo);

    int activateTheUser(String userNo);

    int deleteTheUser(String userNo);

    int addRole(GDRole gdRole);

    GDRole getRole(String roleName);

    int addRoleForUser(int userId, String roleName, String adminName);

    int deleteRoleFromUser(int userId, String roleName);
}
