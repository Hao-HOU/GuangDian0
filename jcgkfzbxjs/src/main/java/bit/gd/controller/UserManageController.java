package bit.gd.controller;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDRole;
import bit.gd.pojo.GDRunningState;
import bit.gd.pojo.GDUser;
import bit.gd.service.IFileService;
import bit.gd.service.IUserManageService;
import bit.gd.util.ShiroMD5Util;
import bit.gd.vo.SmoIntermediateFileVo;
import bit.gd.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:32
 */
@Controller
@RequestMapping("/api")
public class UserManageController {
    @Autowired
    IUserManageService iUserManageService;

    @Autowired
    IFileService iFileService;

    @RequestMapping("add_user.do")
    @ResponseBody
    public ServerResponse addUser(@RequestBody GDUser gdUser) {
        Subject subject = SecurityUtils.getSubject();
        gdUser.setPassword(Const.INITIAL_PASSWORD);
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            gdUser.setPassword(ShiroMD5Util.shiroMD5Encode(gdUser.getUserNo(), gdUser.getPassword()));
            gdUser.setAdminName((String) subject.getSession().getAttribute(Const.CURRENT_USER_NAME));
            int i = iUserManageService.addUser(gdUser);
            if (i > 0) {
                iUserManageService.initializeRunningState(gdUser.getUserNo(),
                        (String) subject.getSession().getAttribute(Const.CURRENT_USER_NAME));
                return ServerResponse.createBySuccessMessage("添加用户成功");
            } else if (i == -1){
                return ServerResponse.createByErrorMessage("该账号已存在");
            } else {
                return ServerResponse.createByErrorMessage("添加用户失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限添加用户");
        }
    }

    @RequestMapping("get_current_user_info.do")
    @ResponseBody
    public ServerResponse<UserVo> getCurrentUserIno() {
        Subject subject = SecurityUtils.getSubject();
        return ServerResponse.createBySuccess(iUserManageService
                .getCurrentUserInfo((String) subject.getPrincipal()));
    }

    @RequestMapping("modify_pwd.do")
    @ResponseBody
    public ServerResponse modifyThePassword(@RequestBody Map<String,Object> map) {
        if (map.get("oldPassword") == null || map.get("newPassword") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();

        Subject subject = SecurityUtils.getSubject();
        String userNo = (String) subject.getPrincipal();

        String credentials = iUserManageService.getCurrentUserPassword(userNo);

        if (credentials.equals(ShiroMD5Util.shiroMD5Encode(userNo, oldPassword))) {
            if (iUserManageService.modifyThePassword(userNo, ShiroMD5Util.shiroMD5Encode(userNo, newPassword)) > 0) {
                return ServerResponse.createBySuccessMessage("密码修改成功");
            } else {
                return ServerResponse.createByErrorMessage("密码修改错误");
            }
        } else {
            return ServerResponse.createByErrorMessage("旧密码输入错误");
        }
    }

    @RequestMapping("modify_user_info.do")
    @ResponseBody
    public ServerResponse modifyUserInfo(@RequestBody Map<String,Object> map) {
        String newName = null;
        String newPhone = null;

        if (map.get("newName") != null && !"".equals(map.get("newName"))) {
            newName = map.get("newName").toString();
        }
        if (map.get("newPhone") != null && !"".equals(map.get("newPhone"))) {
            newPhone = map.get("newPhone").toString();
        }

        Subject subject = SecurityUtils.getSubject();
        if (iUserManageService.modifyUserInfo((String) subject.getPrincipal(), newName, newPhone) > 0) {
            return ServerResponse.createBySuccessMessage("修改信息成功");
        } else {
            return ServerResponse.createByErrorMessage("修改信息失败");
        }
    }

    @RequestMapping("get_all_active_users.do")
    @ResponseBody
    public ServerResponse getAllActiveUsers(@RequestBody Map<String,Object> map) {
        if (map.get("pageNum") == null || map.get("pageSize") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }

        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            return iUserManageService.getAllActiveUsers(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限查看用户列表");
        }
    }

    @RequestMapping("get_all_frozen_users.do")
    @ResponseBody
    public ServerResponse getAllFrozenUsers(@RequestBody Map<String,Object> map) {
        if (map.get("pageNum") == null || map.get("pageSize") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }

        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            return iUserManageService.getAllFrozenUsers(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限查看用户列表");
        }
    }

    @RequestMapping("freeze_the_user.do")
    @ResponseBody
    public ServerResponse freezeTheUser(@RequestBody Map<String,Object> map) {
        if (map.get("userNo") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        String userNo = map.get("userNo").toString();

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            if (iUserManageService.freezeTheUser(userNo) > 0) {
                return ServerResponse.createBySuccessMessage("冻结用户账户成功");
            } else {
                return ServerResponse.createByErrorMessage("冻结用户账户失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限冻结用户");
        }
    }

    @RequestMapping("activate_the_user.do")
    @ResponseBody
    public ServerResponse activateTheUser(@RequestBody Map<String,Object> map) {
        if (map.get("userNo") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        String userNo = map.get("userNo").toString();

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            if (iUserManageService.activateTheUser(userNo) > 0) {
                return ServerResponse.createBySuccessMessage("激活用户账户成功");
            } else {
                return ServerResponse.createByErrorMessage("激活用户账户失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限激活用户");
        }
    }

    @RequestMapping("delete_the_user.do")
    @ResponseBody
    public ServerResponse deleteTheUser(@RequestBody Map<String,Object> map) {
        if (map.get("userNo") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        String userNo = map.get("userNo").toString();

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            if (iUserManageService.deleteTheUser(userNo) > 0) {
                return ServerResponse.createBySuccessMessage("删除用户账户成功");
            } else {
                return ServerResponse.createByErrorMessage("删除用户账户失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限删除用户");
        }
    }

    @RequestMapping("add_role.do")
    @ResponseBody
    public ServerResponse addRole(@RequestBody Map<String,Object> map) {
        if (map.get("newRoleName") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        String newRoleName = map.get("newRoleName").toString();

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            if (iUserManageService.getRole(newRoleName) != null) {
                return ServerResponse.createByErrorMessage("该角色名已存在");
            }

            GDRole gdRole = new GDRole();
            gdRole.setRoleName(newRoleName);
            gdRole.setAdminName((String) subject.getSession().getAttribute(Const.CURRENT_USER_NAME));
            if (iUserManageService.addRole(gdRole) > 0) {
                return ServerResponse.createBySuccessMessage("添加角色成功");
            } else {
                return ServerResponse.createByErrorMessage("添加角色失败");
            }

        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限增加角色");
        }
    }

    @RequestMapping("change_the_role_of_the_user.do")
    @ResponseBody
    public ServerResponse addRoleForUser(@RequestBody Map<String,Object> map) {
        if (map.get("userId") == null || map.get("roleName") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
            int userId = (int) map.get("userId");
            String roleName = map.get("roleName").toString();
            String adminName = (String) subject.getSession().getAttribute(Const.CURRENT_USER_NAME);
            if (iUserManageService.changeTheRoleOfTheUser(userId, roleName, adminName) > 0) {
                return ServerResponse.createBySuccessMessage("修改用户角色成功");
            } else {
                return ServerResponse.createByErrorMessage("修改用户角色失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("当前用户不是管理员，无权限改变用户角色");
        }
    }

    @RequestMapping("get_running_state.do")
    @ResponseBody
    public ServerResponse getRunningState(@RequestBody Map<String,Object> map) {
        if (map.get("moduleName") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        String moduleName = map.get("moduleName").toString();
        Subject subject = SecurityUtils.getSubject();
        String userNo = (String) subject.getPrincipal();
        if (subject.hasRole(moduleName) || subject.hasRole(Const.Role.ROLE_ADMIN)) {
            GDRunningState gdRunningState = iUserManageService.getUserModuleRunningState(userNo, moduleName);
            if (gdRunningState.getRunningStatus() == Const.RunningState.RUNNING) {
                if (iFileService.copySmoIntermediateResult()) {
                    return ServerResponse.createBySuccessCodeMessage(ResponseCode.RUNNING.getCode(), "中间结果已展示", new SmoIntermediateFileVo());
                } else {
                    return ServerResponse.createBySuccessCodeMessage(ResponseCode.RUNNING.getCode(), "暂无中间结果", gdRunningState);
                }

            } else {
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.IDLE.getCode(), "可提交新任务", gdRunningState);
            }
        } else {
            return ServerResponse.createByErrorMessage("已无该模块权限，无法查看运行状态");
        }
    }

}
