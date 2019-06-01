package io.renren.modules.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.CsCustFeeEntity;

import java.util.Map;

/**
 * CS_CUST_FEE
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-23 20:47:10
 */
public interface CsCustFeeService extends IService<CsCustFeeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

