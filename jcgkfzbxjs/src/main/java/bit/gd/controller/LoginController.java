package bit.gd.controller;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.request.User;
import bit.gd.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signIn(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();


        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                subject.login(token);
            } catch (UnknownAccountException uae) {
                return ServerResponse.createByErrorMessage("账号不存在");
            } catch (IncorrectCredentialsException ice) {
                LOGGER.info("用户[{}]密码输入错误", user.getUsername());
                return ServerResponse.createByErrorMessage("密码错误");
            } catch (LockedAccountException lae) {
                return ServerResponse.createByErrorMessage("账号已被冻结");
            } catch (AuthenticationException ae) {
                return ServerResponse.createByErrorMessage("未知错误");
            }
        }

        LOGGER.info("用户[{}]成功登录系统", user.getUsername());
        return ServerResponse.createBySuccess();
    }
}
