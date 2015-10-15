package com.doadway.glodmine.core.dao;

import com.doadway.glodmine.core.model.SpeedType;
import com.doadway.glodmine.core.model.SpeedTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SpeedTypeMapper {
    int countByExample(SpeedTypeExample example);

    int deleteByExample(SpeedTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpeedType record);

    int insertSelective(SpeedType record);

    List<SpeedType> selectByExample(SpeedTypeExample example);

    SpeedType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpeedType record, @Param("example") SpeedTypeExample example);

    int updateByExample(@Param("record") SpeedType record, @Param("example") SpeedTypeExample example);

    int updateByPrimaryKeySelective(SpeedType record);

    int updateByPrimaryKey(SpeedType record);
}