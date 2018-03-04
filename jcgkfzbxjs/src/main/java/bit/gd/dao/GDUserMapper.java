package bit.gd.dao;

import bit.gd.pojo.GDUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface GDUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDUser record);

    int insertSelective(GDUser record);

    GDUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDUser record);

    int updateByPrimaryKey(GDUser record);

    GDUser selectByUserNo(String userNo);

    String selectPasswordByUserNo(String userNo);

    int updatePasswordByUserNo(@Param("userNo") String userNo, @Param("newPassword") String newPassword);

    List<GDUser> selectAllActiveUsers();

    List<GDUser> selectAllFrozenUsers();

    int updateInfoByUserNo(@Param("userNo") String userNo, @Param("newName") String newName,
                           @Param("newPhone") String newPhone);

    int updateStatusByUserNo(@Param("userNo") String userNo, @Param("status") int status);

    int deleteByUserNo(String userNo);
}