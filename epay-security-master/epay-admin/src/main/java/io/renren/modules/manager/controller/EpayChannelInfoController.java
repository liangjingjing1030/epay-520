package io.renren.modules.manager.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.manager.entity.EpayChannelInfoEntity;
import io.renren.modules.manager.service.EpayChannelInfoService;

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
 * 商户渠道表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@RestController
@RequestMapping("manager/epaychannelinfo")
public class EpayChannelInfoController {
    @Autowired
    private EpayChannelInfoService epayChannelInfoService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:epaychannelinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = epayChannelInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:epaychannelinfo:info")
    public R info(@PathVariable("id") Integer id) {
        EpayChannelInfoEntity epayChannelInfo = epayChannelInfoService.getById(id);

        return R.ok().put("epayChannelInfo", epayChannelInfo);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:epaychannelinfo:save")
    public R save(@RequestBody EpayChannelInfoEntity epayChannelInfo) {
        epayChannelInfoService.save(epayChannelInfo);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:epaychannelinfo:update")
    public R update(@RequestBody EpayChannelInfoEntity epayChannelInfo) {
        ValidatorUtils.validateEntity(epayChannelInfo);
        epayChannelInfoService.updateById(epayChannelInfo);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:epaychannelinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        epayChannelInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
