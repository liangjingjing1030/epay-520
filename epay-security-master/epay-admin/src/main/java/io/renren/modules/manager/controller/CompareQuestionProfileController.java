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

import io.renren.modules.manager.entity.CompareQuestionProfileEntity;
import io.renren.modules.manager.service.CompareQuestionProfileService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-05-05 20:06:26
 */
@RestController
@RequestMapping("manager/comparequestionprofile")
public class CompareQuestionProfileController {
    @Autowired
    private CompareQuestionProfileService compareQuestionProfileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:comparequestionprofile:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = compareQuestionProfileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:comparequestionprofile:info")
    public R info(@PathVariable("id") Integer id){
        CompareQuestionProfileEntity compareQuestionProfile = compareQuestionProfileService.getById(id);

        return R.ok().put("compareQuestionProfile", compareQuestionProfile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:comparequestionprofile:save")
    public R save(@RequestBody CompareQuestionProfileEntity compareQuestionProfile){
        compareQuestionProfileService.save(compareQuestionProfile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:comparequestionprofile:update")
    public R update(@RequestBody CompareQuestionProfileEntity compareQuestionProfile){
        ValidatorUtils.validateEntity(compareQuestionProfile);
        compareQuestionProfileService.updateById(compareQuestionProfile);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:comparequestionprofile:delete")
    public R delete(@RequestBody Integer[] ids){
        compareQuestionProfileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
