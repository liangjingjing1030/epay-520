package io.renren.modules.manager.service;

import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.EpayMchInfoEntity;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 商户信息表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
public interface EpayMchInfoService extends IService<EpayMchInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);


    PageUtils queryAuditPage(Map<String, Object> params);
}
