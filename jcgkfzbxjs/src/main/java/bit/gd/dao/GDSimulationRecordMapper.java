package bit.gd.dao;

import bit.gd.pojo.GDSimulationRecord;

import java.util.List;

public interface GDSimulationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDSimulationRecord record);

    int insertSelective(GDSimulationRecord record);

    GDSimulationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDSimulationRecord record);

    int updateByPrimaryKey(GDSimulationRecord record);

    List<GDSimulationRecord> selectAllSimulationRecords();

    List<GDSimulationRecord> selectAuthorizedModulesSimulationRecords(List<String> roles);
}