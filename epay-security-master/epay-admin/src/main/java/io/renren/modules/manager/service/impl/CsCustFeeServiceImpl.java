package io.renren.modules.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.manager.dao.CsCustFeeDao;
import io.renren.modules.manager.entity.CsCustFeeEntity;
import io.renren.modules.manager.service.CsCustFeeService;


@Service("csCustFeeService")
public class CsCustFeeServiceImpl extends ServiceImpl<CsCustFeeDao, CsCustFeeEntity> implements CsCustFeeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CsCustFeeEntity> page = this.page(
                new Query<CsCustFeeEntity>().getPage(params),
                new QueryWrapper<CsCustFeeEntity>()
        );

        return new PageUtils(page);
    }

}
