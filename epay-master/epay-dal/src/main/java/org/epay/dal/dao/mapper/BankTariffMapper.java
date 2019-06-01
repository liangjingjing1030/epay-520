package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.BankTariff;
import org.epay.dal.dao.model.BankTariffExample;

public interface BankTariffMapper {
    long countByExample(BankTariffExample example);

    int deleteByExample(BankTariffExample example);

    int deleteByPrimaryKey(String bank_tariff_id);

    int insert(BankTariff record);

    int insertSelective(BankTariff record);

    List<BankTariff> selectByExample(BankTariffExample example);

    BankTariff selectByPrimaryKey(String bank_tariff_id);

    int updateByExampleSelective(@Param("record") BankTariff record, @Param("example") BankTariffExample example);

    int updateByExample(@Param("record") BankTariff record, @Param("example") BankTariffExample example);

    int updateByPrimaryKeySelective(BankTariff record);

    int updateByPrimaryKey(BankTariff record);
}