package bit.gd.dao;

import bit.gd.pojo.GDRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface GDRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDRole record);

    int insertSelective(GDRole record);

    GDRole selectByPrimaryKey(Integer id);

    GDRole selectByRoleName(String roleName);

    int updateByPrimaryKeySelective(GDRole record);

    int updateByPrimaryKey(GDRole record);

    Set<String> selectAllRolesName();


}