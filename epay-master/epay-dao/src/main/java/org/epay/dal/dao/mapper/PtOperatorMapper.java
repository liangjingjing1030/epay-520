package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtOperator;
import org.epay.dal.dao.model.PtOperatorExample;

public interface PtOperatorMapper {
    long countByExample(PtOperatorExample example);

    int deleteByExample(PtOperatorExample example);

    int deleteByPrimaryKey(String id);

    int insert(PtOperator record);

    int insertSelective(PtOperator record);

    List<PtOperator> selectByExample(PtOperatorExample example);

    PtOperator selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PtOperator record, @Param("example") PtOperatorExample example);

    int updateByExample(@Param("record") PtOperator record, @Param("example") PtOperatorExample example);

    int updateByPrimaryKeySelective(PtOperator record);

    int updateByPrimaryKey(PtOperator record);
}