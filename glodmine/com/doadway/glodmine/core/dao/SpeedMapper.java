package com.doadway.glodmine.core.dao;

import com.doadway.glodmine.core.model.Speed;
import com.doadway.glodmine.core.model.SpeedExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SpeedMapper {
    int countByExample(SpeedExample example);

    int deleteByExample(SpeedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Speed record);

    int insertSelective(Speed record);

    List<Speed> selectByExample(SpeedExample example);

    Speed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Speed record, @Param("example") SpeedExample example);

    int updateByExample(@Param("record") Speed record, @Param("example") SpeedExample example);

    int updateByPrimaryKeySelective(Speed record);

    int updateByPrimaryKey(Speed record);
}