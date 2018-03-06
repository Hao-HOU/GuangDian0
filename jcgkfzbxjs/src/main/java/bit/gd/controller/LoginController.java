package bit.gd.controller;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.request.User;
import bit.gd.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/2/28 22:04
 */
@Controller
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signIn(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();


        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                subject.login(token);
            } catch (UnknownAccountException uae) {
                return ServerResponse.createByErrorMessage("用户名不存在");
            } catch (IncorrectCredentialsException ice) {
                return ServerResponse.createByErrorMessage("密码错误");
            } catch (LockedAccountException lae) {
                return ServerResponse.createByErrorMessage("账号被冻结");
            } catch (AuthenticationException ae) {
                return ServerResponse.createByErrorMessage("未知错误");
            }
        }

        return ServerResponse.createBySuccess();
    }
}
