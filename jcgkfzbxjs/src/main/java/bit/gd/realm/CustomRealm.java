package bit.gd.realm;

import bit.gd.dao.GDRUserRoleMapper;
import bit.gd.dao.GDRoleMapper;
import bit.gd.dao.GDUserMapper;
import bit.gd.pojo.GDUser;
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

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String userNo= (String) token.getPrincipal();

        GDUser gdUser = gdUserMapper.selectByUserNo(userNo);

        if (gdUser != null) {
            Object principal = gdUser.getUserNo();
            Object credential = gdUser.getPassword();
            String realName = getName();
            ByteSource credentialSalt = ByteSource.Util.bytes(userNo);

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credential,
                    credentialSalt, realName);

            return info;

        } else {
            LOGGER.info("用户账号不存在");
            throw new UnknownAccountException();
        }
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) 	{
        String userNo = (String) getAvailablePrincipal(principals);

        GDUser gdUser = gdUserMapper.selectByUserNo(userNo);

        Set<String> roles = new HashSet<>();
        List<Integer> rolesId = gdrUserRoleMapper.selectRolesIdByUserId(gdUser.getId());
        if (!rolesId.isEmpty()) {
            for (int id : rolesId) {
                roles.add(gdRoleMapper.selectByPrimaryKey(id).getRoleName());
            }
        }

        SecurityUtils.getSubject().getSession().setAttribute("currentUserName", gdUser.getName());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }
}

