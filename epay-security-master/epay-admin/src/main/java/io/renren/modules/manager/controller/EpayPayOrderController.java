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

import io.renren.modules.manager.entity.EpayPayOrderEntity;
import io.renren.modules.manager.service.EpayPayOrderService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 订单支付表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@RestController
@RequestMapping("manager/epaypayorder")
public class EpayPayOrderController {
    @Autowired
    private EpayPayOrderService epayPayOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:epaypayorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = epayPayOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{payOrderId}")
    @RequiresPermissions("manager:epaypayorder:info")
    public R info(@PathVariable("payOrderId") String payOrderId){
        EpayPayOrderEntity epayPayOrder = epayPayOrderService.getById(payOrderId);

        return R.ok().put("epayPayOrder", epayPayOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:epaypayorder:save")
    public R save(@RequestBody EpayPayOrderEntity epayPayOrder){
        epayPayOrderService.save(epayPayOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:epaypayorder:update")
    public R update(@RequestBody EpayPayOrderEntity epayPayOrder){
        ValidatorUtils.validateEntity(epayPayOrder);
        epayPayOrderService.updateById(epayPayOrder);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:epaypayorder:delete")
    public R delete(@RequestBody String[] payOrderIds){
        epayPayOrderService.removeByIds(Arrays.asList(payOrderIds));

        return R.ok();
    }

}
