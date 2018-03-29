package bit.gd.dao;

import bit.gd.pojo.GDParameterOpc;

import java.util.List;

public interface GDParameterOpcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GDParameterOpc record);

    int insertSelective(GDParameterOpc record);

    GDParameterOpc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GDParameterOpc record);

    int updateByPrimaryKey(GDParameterOpc record);

    List<GDParameterOpc> selectByRecord(GDParameterOpc record);

    Double selectMaxloop(String userNo);
}