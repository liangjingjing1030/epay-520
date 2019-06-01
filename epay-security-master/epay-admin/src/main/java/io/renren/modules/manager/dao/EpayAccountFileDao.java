package io.renren.modules.manager.dao;

import io.renren.modules.manager.entity.EpayAccountFileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账单文件表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Mapper
public interface EpayAccountFileDao extends BaseMapper<EpayAccountFileEntity> {
	
}
