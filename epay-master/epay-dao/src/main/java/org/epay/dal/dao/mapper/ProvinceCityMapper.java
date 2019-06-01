package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.ProvinceCity;

import java.util.List;

public interface ProvinceCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvinceCity record);

    int insertSelective(ProvinceCity record);

    ProvinceCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvinceCity record);

    int updateByPrimaryKey(ProvinceCity record);

    List<ProvinceCity> selectAllProvince();

    List<ProvinceCity> selectByFatherId(int id);
}