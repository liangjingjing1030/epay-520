package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.PaycompanyOrderDao;
import io.renren.modules.manager.entity.PaycompanyOrderEntity;
import io.renren.modules.manager.service.PaycompanyOrderService;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("paycompanyOrderService")
public class PaycompanyOrderServiceImpl extends ServiceImpl<PaycompanyOrderDao, PaycompanyOrderEntity>
        implements PaycompanyOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaycompanyOrderEntity> page =
                this.page(new Query<PaycompanyOrderEntity>().getPage(params),
                    new QueryWrapper<PaycompanyOrderEntity>());

        return new PageUtils(page);
    }


    @Override
    public void downfile(Map<String, Object> params) {

    }


    @Override
    public void compare(Map<String, Object> params) {
        // TODO Auto-generated method stub

    }

}
