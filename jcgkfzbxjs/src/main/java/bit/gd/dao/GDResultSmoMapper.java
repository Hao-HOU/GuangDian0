package bit.gd.dao;

import bit.gd.pojo.GDResultSmo;

public interface GDResultSmoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDResultSmo record);

    int insertSelective(GDResultSmo record);

    GDResultSmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDResultSmo record);

    int updateByPrimaryKey(GDResultSmo record);
}