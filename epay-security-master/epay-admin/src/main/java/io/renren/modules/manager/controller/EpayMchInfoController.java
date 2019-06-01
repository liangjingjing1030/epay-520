package io.renren.modules.manager.controller;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.manager.entity.EpayMchInfoEntity;
import io.renren.modules.manager.service.EpayMchInfoService;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商户信息表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@RestController
@RequestMapping("manager/epaymchinfo")
public class EpayMchInfoController {
    @Autowired
    private EpayMchInfoService epayMchInfoService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:epaymchinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = epayMchInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{mchId}")
    @RequiresPermissions("manager:epaymchinfo:info")
    public R info(@PathVariable("mchId") String mchId) {
        EpayMchInfoEntity epayMchInfo = epayMchInfoService.getById(mchId);

        return R.ok().put("epayMchInfo", epayMchInfo);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:epaymchinfo:save")
    public R save(@RequestBody EpayMchInfoEntity epayMchInfo) {
        epayMchInfoService.save(epayMchInfo);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:epaymchinfo:update")
    public R update(@RequestBody EpayMchInfoEntity epayMchInfo) {
        ValidatorUtils.validateEntity(epayMchInfo);
        epayMchInfoService.updateById(epayMchInfo);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:epaymchinfo:delete")
    public R delete(@RequestBody String[] mchIds) {
        epayMchInfoService.removeByIds(Arrays.asList(mchIds));

        return R.ok();
    }


    /**
     * 获取未审核及审核拒绝商户
     */
    @RequestMapping("/getauditlist")
    @RequiresPermissions("manager:epaymchinfo:list")
    public R getauditlist(@RequestParam Map<String, Object> params) {
        PageUtils page = epayMchInfoService.queryAuditPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 审核
     */
    /*@RequestMapping("/updateHandle")
    @RequiresPermissions("manager:epaymchinfo:update")
    public R updateHandle(@RequestBody EpayMchInfoEntity epayMchInfo) {
        epayMchInfo.setAuditTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        ValidatorUtils.validateEntity(epayMchInfo);
        return R.ok();
    }*/

    /**
     * 审核
     * update by GaoLiang
     * 2019年5月22日09:48:07
     */
    @RequestMapping("/updateHandle")
    @RequiresPermissions("manager:epaymchinfo:update")
    public R updateHandle(@RequestBody EpayMchInfoEntity epayMchInfo) {
        // 设置审核时间
        epayMchInfo.setAuditTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        ValidatorUtils.validateEntity(epayMchInfo);
        boolean b = epayMchInfoService.updateById(epayMchInfo);
        // 判断更新是否成功
        if(b) {
            // 更新审核状态成功，且审核是通过状态，则更新商户的可用状态为可用
            int auditStatus = epayMchInfo.getAuditStatus();//审核状态 0-未审核,1-已审核,2-拒绝
            if (auditStatus == 1) {
                epayMchInfo.setMchStatus(1);//商户状态 0-未激活,1-使用中,2-停止使用
                boolean updateMchStatusOk = epayMchInfoService.updateById(epayMchInfo);
                if(updateMchStatusOk) {
                    // TODO 调用短信接口,通知mgr审核通过

                    return R.ok();
                } else {
                    return R.error("更新商户可用状态失败!");
                }
            } else {
                // 审核状态为 2-拒绝
                return R.ok();
            }
        } else {
            return R.error("更新审核信息失败!");
        }
    }

}
