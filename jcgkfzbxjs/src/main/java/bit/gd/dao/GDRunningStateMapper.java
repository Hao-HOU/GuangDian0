package bit.gd.dao;

import bit.gd.pojo.GDRunningState;
import org.apache.ibatis.annotations.Param;

public interface GDRunningStateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDRunningState record);

    int insertSelective(GDRunningState record);

    GDRunningState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDRunningState record);

    int updateByPrimaryKey(GDRunningState record);

    int updateByUserNoAndModuleName(@Param("userNo") String userNo,
                                    @Param("moduleName") String moduleName,
                                    @Param("runningStatus") int runningStatus);

    int executeSuccessResetAndPlus(@Param("userNo") String userNo,
                                   @Param("moduleName") String moduleName,
                                   @Param("runningStatus") int runningStatus);

    GDRunningState selectByUserUserNoAndModuleName(@Param("userNo") String userNo,
                                                   @Param("moduleName") String moduleName);

    int resetRunningStateByUserNoAndModuleName(@Param("userNo") String userNo,
                                               @Param("moduleName") String moduleName);
}