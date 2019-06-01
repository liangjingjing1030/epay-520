package io.renren.modules.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.EpayChannelInfoEntity;

import java.util.Map;

/**
 * 商户渠道表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
public interface EpayChannelInfoService extends IService<EpayChannelInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

