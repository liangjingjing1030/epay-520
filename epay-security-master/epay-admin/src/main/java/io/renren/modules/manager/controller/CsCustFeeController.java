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

import io.renren.modules.manager.entity.CsCustFeeEntity;
import io.renren.modules.manager.service.CsCustFeeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * CS_CUST_FEE
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-23 20:47:10
 */
@RestController
@RequestMapping("manager/cscustfee")
public class CsCustFeeController {
    @Autowired
    private CsCustFeeService csCustFeeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("manager:cscustfee:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = csCustFeeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seqNo}")
    @RequiresPermissions("manager:cscustfee:info")
    public R info(@PathVariable("seqNo") String seqNo){
        CsCustFeeEntity csCustFee = csCustFeeService.getById(seqNo);

        return R.ok().put("csCustFee", csCustFee);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:cscustfee:save")
    public R save(@RequestBody CsCustFeeEntity csCustFee){
        csCustFeeService.save(csCustFee);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:cscustfee:update")
    public R update(@RequestBody CsCustFeeEntity csCustFee){
        ValidatorUtils.validateEntity(csCustFee);
        csCustFeeService.updateById(csCustFee);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:cscustfee:delete")
    public R delete(@RequestBody String[] seqNos){
        csCustFeeService.removeByIds(Arrays.asList(seqNos));

        return R.ok();
    }

}
