package io.renren.modules.manager.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.manager.entity.EpayAccountBookEntity;
import io.renren.modules.manager.service.EpayAccountBookService;

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
 * 账单明细表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@RestController
@RequestMapping("manager/epayaccountbook")
public class EpayAccountBookController {
    @Autowired
    private EpayAccountBookService epayAccountBookService;


    /**
     * 列表
     */
    @SysLog("记录日志注解，查询账单详细")
    @RequestMapping("/list")
    @RequiresPermissions("manager:epayaccountbook:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = epayAccountBookService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{accountBookId}")
    @RequiresPermissions("manager:epayaccountbook:info")
    public R info(@PathVariable("accountBookId") String accountBookId) {
        EpayAccountBookEntity epayAccountBook = epayAccountBookService.getById(accountBookId);

        return R.ok().put("epayAccountBook", epayAccountBook);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("manager:epayaccountbook:save")
    public R save(@RequestBody EpayAccountBookEntity epayAccountBook) {
        epayAccountBookService.save(epayAccountBook);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("manager:epayaccountbook:update")
    public R update(@RequestBody EpayAccountBookEntity epayAccountBook) {
        ValidatorUtils.validateEntity(epayAccountBook);
        epayAccountBookService.updateById(epayAccountBook);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("manager:epayaccountbook:delete")
    public R delete(@RequestBody String[] accountBookIds) {
        epayAccountBookService.removeByIds(Arrays.asList(accountBookIds));

        return R.ok();
    }

}
