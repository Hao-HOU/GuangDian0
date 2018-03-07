package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.common.ServerResponse;
import bit.gd.dao.GDRUserRoleMapper;
import bit.gd.dao.GDRoleMapper;
import bit.gd.dao.GDUserMapper;
import bit.gd.pojo.GDRUserRole;
import bit.gd.pojo.GDRole;
import bit.gd.pojo.GDUser;
import bit.gd.service.IUserManageService;
import bit.gd.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:38
 */
@Service
public class UserManageServiceImpl implements IUserManageService {
    @Autowired
    GDUserMapper gdUserMapper;

    @Autowired
    GDRoleMapper gdRoleMapper;

    @Autowired
    GDRUserRoleMapper gdrUserRoleMapper;

    public int addUser(GDUser gdUser) {
        if (gdUserMapper.selectByUserNo(gdUser.getUserNo()) != null) {
            return -1;
        }
        Date now = new Date();
        gdUser.setCrateTime(now);
        gdUser.setUpdateTime(now);
        return gdUserMapper.insertSelective(gdUser);
    }

    public UserVo getCurrentUserInfo(String userNo) {
        return assembleUserVo(gdUserMapper.selectByUserNo(userNo));
    }

    public String getCurrentUserPassword(String userNo) {
        return gdUserMapper.selectPasswordByUserNo(userNo);
    }

    public Set<String> getAllRolesName() {
        return gdRoleMapper.selectAllRolesName();
    }

    public List<GDRole> getAllRoles() {
        return gdRoleMapper.selectAllRoles();
    }

    public int modifyThePassword (String userNo, String newPassword) {
        return gdUserMapper.updatePasswordByUserNo(userNo, newPassword);
    }

    public ServerResponse<PageInfo> getAllActiveUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<GDUser> activeUsersList = gdUserMapper.selectAllActiveUsers();
        List<UserVo> userVoList = Lists.newArrayList();
        for (GDUser gdUser : activeUsersList) {
            UserVo userVo = assembleUserVo(gdUser);
            userVoList.add(userVo);
        }

        PageInfo pageInfo = new PageInfo(activeUsersList);
        pageInfo.setList(userVoList);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<PageInfo> getAllFrozenUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<GDUser> frozenUsersList = gdUserMapper.selectAllFrozenUsers();
        List<UserVo> userVoList = Lists.newArrayList();
        for (GDUser gdUser : frozenUsersList) {
            UserVo userVo = assembleUserVo(gdUser);
            userVoList.add(userVo);
        }

        PageInfo pageInfo = new PageInfo(frozenUsersList);
        pageInfo.setList(userVoList);

        return ServerResponse.createBySuccess(pageInfo);
    }

    private UserVo assembleUserVo(GDUser gdUser) {
        UserVo userVo = new UserVo();
        userVo.setId(gdUser.getId());
        userVo.setUserNo(gdUser.getUserNo());
        userVo.setName(gdUser.getName());
        userVo.setPhone(gdUser.getPhone());

        userVo.setRoles(getUserRoles(gdUser.getId()));

        return userVo;
    }

    public Set<String> getUserRoles(int userId) {
        Set<String> roles = new HashSet<>();
        List<Integer> rolesId = gdrUserRoleMapper.selectRolesIdByUserId(userId);


        if (!rolesId.isEmpty()) {
            for (int id : rolesId) {
                roles.add(gdRoleMapper.selectByPrimaryKey(id).getRoleName());
            }
        }

        return roles;
    }

    public int modifyUserInfo(String userNo, String newName, String newPhone) {
        return gdUserMapper.updateInfoByUserNo(userNo, newName, newPhone);
    }

    public int freezeTheUser(String userNo) {
        return gdUserMapper.updateStatusByUserNo(userNo, Const.Status.FROZEN);
    }

    public int activateTheUser(String userNo) {
        return gdUserMapper.updateStatusByUserNo(userNo, Const.Status.ACTIVE);
    }

    public int deleteTheUser(String userNo) {
        return gdUserMapper.deleteByUserNo(userNo);
    }

    public int addRole(GDRole gdRole) {
        return gdRoleMapper.insert(gdRole);
    }

    public GDRole getRole(String roleName) {
        return gdRoleMapper.selectByRoleName(roleName);
    }

    private int addRoleForUser(int userId, String roleName, String adminName) {
        GDRole gdRole = gdRoleMapper.selectByRoleName(roleName);
        GDRUserRole gdrUserRole = new GDRUserRole();
        gdrUserRole.setUserId(userId);
        gdrUserRole.setRoleId(gdRole.getId());
        gdrUserRole.setAdminName(adminName);

        return gdrUserRoleMapper.insert(gdrUserRole);
    }

    private int deleteRoleFromUser(int userId, String roleName) {
        return gdrUserRoleMapper.deleteRoleFromUser(userId, gdRoleMapper.selectByRoleName(roleName).getId());
    }

    public int changeTheRoleOfTheUser(int userId, String roleName, String adminName) {
        GDRUserRole gdrUserRole = gdrUserRoleMapper.selectByUserIdAndRoleId(userId,
                gdRoleMapper.selectByRoleName(roleName).getId());
        if (gdrUserRole == null) {
            return addRoleForUser(userId, roleName, adminName);
        } else {
            return deleteRoleFromUser(userId, roleName);
        }
    }
}
