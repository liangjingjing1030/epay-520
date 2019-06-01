package io.renren.modules.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.EpayAccountFileEntity;

import java.util.Map;

/**
 * 账单文件表
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
public interface EpayAccountFileService extends IService<EpayAccountFileEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

