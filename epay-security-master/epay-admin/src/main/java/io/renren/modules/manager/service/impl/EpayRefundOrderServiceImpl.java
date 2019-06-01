package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.EpayRefundOrderDao;
import io.renren.modules.manager.entity.EpayRefundOrderEntity;
import io.renren.modules.manager.service.EpayRefundOrderService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("epayRefundOrderService")
public class EpayRefundOrderServiceImpl extends ServiceImpl<EpayRefundOrderDao, EpayRefundOrderEntity>
        implements EpayRefundOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String refund_order_id = (String) params.get("refund_order_id");
        IPage<EpayRefundOrderEntity> page =
                this.page(new Query<EpayRefundOrderEntity>().getPage(params),
                    new QueryWrapper<EpayRefundOrderEntity>().eq(StringUtils.isNotBlank(refund_order_id),
                        "refund_order_id", refund_order_id));

        return new PageUtils(page);
    }

}
