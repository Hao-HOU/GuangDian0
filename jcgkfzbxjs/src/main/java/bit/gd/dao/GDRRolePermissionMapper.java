package bit.gd.dao;

import bit.gd.pojo.GDRRolePermission;

public interface GDRRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDRRolePermission record);

    int insertSelective(GDRRolePermission record);

    GDRRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDRRolePermission record);

    int updateByPrimaryKey(GDRRolePermission record);
}