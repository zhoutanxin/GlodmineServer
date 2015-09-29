package com.doadway.glodmine.core.dao;

import com.doadway.glodmine.core.model.IncomeType;
import com.doadway.glodmine.core.model.IncomeTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IncomeTypeMapper {
    int countByExample(IncomeTypeExample example);

    int deleteByExample(IncomeTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeType record);

    int insertSelective(IncomeType record);

    List<IncomeType> selectByExample(IncomeTypeExample example);

    IncomeType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeType record, @Param("example") IncomeTypeExample example);

    int updateByExample(@Param("record") IncomeType record, @Param("example") IncomeTypeExample example);

    int updateByPrimaryKeySelective(IncomeType record);

    int updateByPrimaryKey(IncomeType record);
}