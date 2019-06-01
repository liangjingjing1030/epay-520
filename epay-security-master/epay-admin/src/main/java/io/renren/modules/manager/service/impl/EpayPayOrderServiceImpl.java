package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.EpayPayOrderDao;
import io.renren.modules.manager.entity.EpayPayOrderEntity;
import io.renren.modules.manager.service.EpayPayOrderService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("epayPayOrderService")
public class EpayPayOrderServiceImpl extends ServiceImpl<EpayPayOrderDao, EpayPayOrderEntity> implements
        EpayPayOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String pay_order_id = (String) params.get("pay_order_id");
        IPage<EpayPayOrderEntity> page =
                this.page(new Query<EpayPayOrderEntity>().getPage(params),
                    new QueryWrapper<EpayPayOrderEntity>().eq(StringUtils.isNotBlank(pay_order_id),
                        "pay_order_id", pay_order_id));

        return new PageUtils(page);
    }

}
