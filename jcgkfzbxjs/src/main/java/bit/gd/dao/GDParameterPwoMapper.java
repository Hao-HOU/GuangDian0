package bit.gd.dao;

import bit.gd.pojo.GDParameterPwo;

public interface GDParameterPwoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDParameterPwo record);

    int insertSelective(GDParameterPwo record);

    GDParameterPwo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDParameterPwo record);

    int updateByPrimaryKey(GDParameterPwo record);
}