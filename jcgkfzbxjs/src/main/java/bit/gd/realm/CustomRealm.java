package bit.gd.realm;

import bit.gd.common.Const;
import bit.gd.dao.GDRUserRoleMapper;
import bit.gd.dao.GDRoleMapper;
import bit.gd.dao.GDUserMapper;
import bit.gd.pojo.GDUser;
import bit.gd.service.IUserManageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/2/28 21:54
 */
public class CustomRealm extends AuthorizingRealm {
    private Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);
    //设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    @Autowired
    GDUserMapper gdUserMapper;

    @Autowired
    GDRUserRoleMapper gdrUserRoleMapper;

    @Autowired
    GDRoleMapper gdRoleMapper;

    @Autowired
    IUserManageService iUserManageService;

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String userNo= (String) token.getPrincipal();

        GDUser gdUser = gdUserMapper.selectByUserNo(userNo);

        if (gdUser != null) {
            if (gdUser.getStatus() == Const.Status.ACTIVE) {
                Object principal = gdUser.getUserNo();
                Object credential = gdUser.getPassword();
                String realName = getName();
                ByteSource credentialSalt = ByteSource.Util.bytes(userNo);

                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credential,
                        credentialSalt, realName);

                return info;
            } else {
                LOGGER.info("用户账号[{}]已被冻结", userNo);
                throw new LockedAccountException();
            }
        } else {
            LOGGER.info("用户账号[{}]不存在", userNo);
            throw new UnknownAccountException();
        }
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) 	{
        String userNo = (String) getAvailablePrincipal(principals);

        GDUser gdUser = gdUserMapper.selectByUserNo(userNo);

        SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER_NAME, gdUser.getName());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取当前用户的所有角色
        simpleAuthorizationInfo.setRoles(iUserManageService.getUserRoles(gdUser.getId()));

        return simpleAuthorizationInfo;
    }
}

