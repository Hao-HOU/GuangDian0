package bit.gd.dao;

import bit.gd.pojo.GDRole;

public interface GDRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDRole record);

    int insertSelective(GDRole record);

    GDRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDRole record);

    int updateByPrimaryKey(GDRole record);
}