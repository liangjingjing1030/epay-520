package io.renren.modules.manager.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.manager.entity.PaycompanyOrderEntity;
import io.renren.modules.manager.service.PaycompanyOrderService;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-23 21:24:36
 */
@RestController
@RequestMapping("manager/paycompanyorder")
public class PaycompanyOrderController {
    @Autowired
    private PaycompanyOrderService paycompanyOrderService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:paycompanyorder:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = paycompanyOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seqNo}")
    @RequiresPermissions("manager:paycompanyorder:info")
    public R info(@PathVariable("seqNo") Integer seqNo) {
        PaycompanyOrderEntity paycompanyOrder = paycompanyOrderService.getById(seqNo);

        return R.ok().put("paycompanyOrder", paycompanyOrder);
    }


    /**
     * 下载对账文件
     */
    @RequestMapping("/downfile")
    public R downfile(@RequestParam Map<String, Object> params) {
        String channel = (String) params.get("channel");
        String platform = (String) params.get("platform");
        String starttime = (String) params.get("starttime");
        if (channel == null || "".equals(channel)) {
            return R.error("合作机构不能为空");
        }
        if (platform == null || "".equals(platform)) {
            return R.error("平台不能为空");
        }
        if (starttime == null || "".equals(starttime)) {
            return R.error("交易日期不能为空");
        }
        paycompanyOrderService.downfile(params);

        return R.ok();
    }


    @RequestMapping("/compare")
    public R compare(@RequestParam Map<String, Object> params) {
        String channel = (String) params.get("channel");
        String platform = (String) params.get("platform");
        String starttime = (String) params.get("starttime");
        if (channel == null || "".equals(channel)) {
            return R.error("合作机构不能为空");
        }
        if (platform == null || "".equals(platform)) {
            return R.error("平台不能为空");
        }
        if (starttime == null || "".equals(starttime)) {
            return R.error("交易日期不能为空");
        }
        paycompanyOrderService.compare(params);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:paycompanyorder:update")
    public R update(@RequestBody PaycompanyOrderEntity paycompanyOrder) {
        ValidatorUtils.validateEntity(paycompanyOrder);
        paycompanyOrderService.updateById(paycompanyOrder);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:paycompanyorder:delete")
    public R delete(@RequestBody Integer[] seqNos) {
        paycompanyOrderService.removeByIds(Arrays.asList(seqNos));

        return R.ok();
    }

}
