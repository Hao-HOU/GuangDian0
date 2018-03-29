package bit.gd.dao;

import bit.gd.pojo.GDResultPwo;

public interface GDResultPwoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDResultPwo record);

    int insertSelective(GDResultPwo record);

    GDResultPwo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDResultPwo record);

    int updateByPrimaryKey(GDResultPwo record);
}