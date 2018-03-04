package bit.gd.dao;

import bit.gd.pojo.GDUser;

public interface GDUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDUser record);

    int insertSelective(GDUser record);

    GDUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDUser record);

    int updateByPrimaryKey(GDUser record);

    GDUser selectByUserNo(String userNo);
}