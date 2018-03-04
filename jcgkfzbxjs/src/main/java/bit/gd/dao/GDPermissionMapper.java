package bit.gd.dao;

import bit.gd.pojo.GDPermission;

public interface GDPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDPermission record);

    int insertSelective(GDPermission record);

    GDPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDPermission record);

    int updateByPrimaryKey(GDPermission record);
}