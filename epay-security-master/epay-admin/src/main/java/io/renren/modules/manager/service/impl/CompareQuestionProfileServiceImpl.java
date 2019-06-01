package io.renren.modules.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.manager.dao.CompareQuestionProfileDao;
import io.renren.modules.manager.entity.CompareQuestionProfileEntity;
import io.renren.modules.manager.service.CompareQuestionProfileService;


@Service("compareQuestionProfileService")
public class CompareQuestionProfileServiceImpl extends ServiceImpl<CompareQuestionProfileDao, CompareQuestionProfileEntity> implements CompareQuestionProfileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CompareQuestionProfileEntity> page = this.page(
                new Query<CompareQuestionProfileEntity>().getPage(params),
                new QueryWrapper<CompareQuestionProfileEntity>()
        );

        return new PageUtils(page);
    }

}
