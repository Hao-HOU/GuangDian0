package bit.gd.service.impl;

import bit.gd.dao.GDUserMapper;
import bit.gd.pojo.GDUser;
import bit.gd.service.IUserManageService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author houhao
 * @email houhao118@163.com
 * @date 2018/3/2 16:38
 */
@Service
public class UserManageServiceImpl implements IUserManageService {
    @Autowired
    GDUserMapper gdUserMapper;

    public int addUser(GDUser gdUser) {
        if (gdUserMapper.selectByUserNo(gdUser.getUserNo()) != null) {
            return -1;
        }
        Date now = new Date();
        gdUser.setCrateTime(now);
        gdUser.setUpdateTime(now);
        return gdUserMapper.insertSelective(gdUser);
    }

    public GDUser getCurrentUserInfo(String userNo) {
        return gdUserMapper.selectByUserNo(userNo);
    }
}
