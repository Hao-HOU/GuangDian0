package bit.gd.dao;

import bit.gd.pojo.GDParameterSmo;

import java.util.List;

public interface GDParameterSmoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDParameterSmo record);

    int insertSelective(GDParameterSmo record);

    GDParameterSmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDParameterSmo record);

    int updateByPrimaryKey(GDParameterSmo record);

    List<GDParameterSmo> selectByRecord(GDParameterSmo record);
}