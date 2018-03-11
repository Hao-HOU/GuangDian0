package bit.gd.dao;

import bit.gd.pojo.GDSimulationRecord;
import bit.gd.vo.SearchSimulationRecordsRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GDSimulationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDSimulationRecord record);

    int insertSelective(GDSimulationRecord record);

    GDSimulationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDSimulationRecord record);

    int updateByPrimaryKey(GDSimulationRecord record);

    List<GDSimulationRecord> selectAllSimulationRecords(SearchSimulationRecordsRequest searchSimulationRecordsRequest);

    List<GDSimulationRecord> selectAuthorizedModulesSimulationRecords(SearchSimulationRecordsRequest searchSimulationRecordsRequest);

    GDSimulationRecord selectByModuleNameAndParametersId(@Param("moduleName") String moduleName,
                                              @Param("parametersId") int parametersId);
}