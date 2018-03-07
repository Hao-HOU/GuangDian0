package bit.gd.dao;

import bit.gd.pojo.GDParameterSmo;

public interface GDParameterSmoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDParameterSmo record);

    int insertSelective(GDParameterSmo record);

    GDParameterSmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDParameterSmo record);

    int updateByPrimaryKey(GDParameterSmo record);

    GDParameterSmo selectIdByRecord(GDParameterSmo record);
}