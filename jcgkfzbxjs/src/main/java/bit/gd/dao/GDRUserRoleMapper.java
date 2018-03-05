package bit.gd.dao;

import bit.gd.pojo.GDRUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GDRUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDRUserRole record);

    int insertSelective(GDRUserRole record);

    GDRUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDRUserRole record);

    int updateByPrimaryKey(GDRUserRole record);

    List<Integer> selectRolesIdByUserId(Integer userId);

    int deleteRoleFromUser(@Param("userId") int userId, @Param("roleId") int roleId);

    GDRUserRole selectByUserIdAndRoleId(@Param("userId") int userId, @Param("roleId") int roleId);
}