package bit.gd.controller;

import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDUser;
import bit.gd.service.IUserManageService;
import bit.gd.util.ShiroMD5Util;
import bit.gd.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:32
 */
@Controller
public class UserManageController {
    @Autowired
    IUserManageService iUserManageService;

    @RequestMapping("add_user.do")
    @ResponseBody
    public ServerResponse addUser(@RequestBody GDUser gdUser) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            gdUser.setPassword(ShiroMD5Util.shiroMD5Encode(gdUser));
            gdUser.setAdminName((String) subject.getSession().getAttribute("currentUserName"));
            int i = iUserManageService.addUser(gdUser);
            if (i > 0) {
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
        GDUser gdUser = iUserManageService.getCurrentUserInfo((String) subject.getPrincipal());
        UserVo userVo = new UserVo();
        userVo.setName(gdUser.getName());
        userVo.setUserNo(gdUser.getUserNo());
        userVo.setPhone(gdUser.getPhone());

        Set<String> roles = new HashSet<>();
        if (subject.hasRole("smo")) {
            roles.add("smo");
        }
        if (subject.hasRole("opc")) {
            roles.add("opc");
        }
        if (subject.hasRole("smpwo")) {
            roles.add("smpwo");
        }
        if (subject.hasRole("pdod")) {
            roles.add("pdod");
        }

        userVo.setRoles(roles);

        return ServerResponse.createBySuccess(userVo);
    }
}
