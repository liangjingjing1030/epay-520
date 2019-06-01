package io.renren.modules.manager.service;

import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.PaycompanyOrderEntity;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-23 21:24:36
 */
public interface PaycompanyOrderService extends IService<PaycompanyOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void downfile(Map<String, Object> params);


    void compare(Map<String, Object> params);
}
