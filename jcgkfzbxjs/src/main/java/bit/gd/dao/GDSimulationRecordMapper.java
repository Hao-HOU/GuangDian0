package bit.gd.dao;

import bit.gd.pojo.GDSimulationRecord;

public interface GDSimulationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDSimulationRecord record);

    int insertSelective(GDSimulationRecord record);

    GDSimulationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDSimulationRecord record);

    int updateByPrimaryKey(GDSimulationRecord record);
}