package io.renren.modules.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.manager.entity.CompareQuestionProfileEntity;

import java.util.Map;

/**
 * 
 *
 * @author liyongshuai
 * @email ${email}
 * @date 2019-05-05 20:06:26
 */
public interface CompareQuestionProfileService extends IService<CompareQuestionProfileEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

