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

import io.renren.modules.manager.entity.EpayAccountFileEntity;
import io.renren.modules.manager.service.EpayAccountFileService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 账单文件表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@RestController
@RequestMapping("manager/epayaccountfile")
public class EpayAccountFileController {
    @Autowired
    private EpayAccountFileService epayAccountFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:epayaccountfile:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = epayAccountFileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fileId}")
    @RequiresPermissions("manager:epayaccountfile:info")
    public R info(@PathVariable("fileId") String fileId){
        EpayAccountFileEntity epayAccountFile = epayAccountFileService.getById(fileId);

        return R.ok().put("epayAccountFile", epayAccountFile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:epayaccountfile:save")
    public R save(@RequestBody EpayAccountFileEntity epayAccountFile){
        epayAccountFileService.save(epayAccountFile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:epayaccountfile:update")
    public R update(@RequestBody EpayAccountFileEntity epayAccountFile){
        ValidatorUtils.validateEntity(epayAccountFile);
        epayAccountFileService.updateById(epayAccountFile);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:epayaccountfile:delete")
    public R delete(@RequestBody String[] fileIds){
        epayAccountFileService.removeByIds(Arrays.asList(fileIds));

        return R.ok();
    }

}
