package io.renren.modules.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.EpayRefundOrderEntity;

import java.util.Map;

/**
 * 订单退款表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
public interface EpayRefundOrderService extends IService<EpayRefundOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

