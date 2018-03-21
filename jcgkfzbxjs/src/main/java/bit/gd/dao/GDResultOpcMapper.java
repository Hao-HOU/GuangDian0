package bit.gd.dao;

import bit.gd.pojo.GDResultOpc;

public interface GDResultOpcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDResultOpc record);

    int insertSelective(GDResultOpc record);

    GDResultOpc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDResultOpc record);

    int updateByPrimaryKey(GDResultOpc record);
}