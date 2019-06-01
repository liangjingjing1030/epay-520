package io.renren.modules.manager.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.manager.entity.EpayRefundOrderEntity;
import io.renren.modules.manager.service.EpayRefundOrderService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 订单退款表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@RestController
@RequestMapping("manager/epayrefundorder")
public class EpayRefundOrderController {
    @Autowired
    private EpayRefundOrderService epayRefundOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:epayrefundorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = epayRefundOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{refundOrderId}")
    @RequiresPermissions("manager:epayrefundorder:info")
    public R info(@PathVariable("refundOrderId") String refundOrderId){
        EpayRefundOrderEntity epayRefundOrder = epayRefundOrderService.getById(refundOrderId);

        return R.ok().put("epayRefundOrder", epayRefundOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:epayrefundorder:save")
    public R save(@RequestBody EpayRefundOrderEntity epayRefundOrder){
        epayRefundOrderService.save(epayRefundOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:epayrefundorder:update")
    public R update(@RequestBody EpayRefundOrderEntity epayRefundOrder){
        ValidatorUtils.validateEntity(epayRefundOrder);
        epayRefundOrderService.updateById(epayRefundOrder);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:epayrefundorder:delete")
    public R delete(@RequestBody String[] refundOrderIds){
        epayRefundOrderService.removeByIds(Arrays.asList(refundOrderIds));

        return R.ok();
    }

}
