package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.EpayMchInfoDao;
import io.renren.modules.manager.entity.EpayMchInfoEntity;
import io.renren.modules.manager.service.EpayMchInfoService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("epayMchInfoService")
public class EpayMchInfoServiceImpl extends ServiceImpl<EpayMchInfoDao, EpayMchInfoEntity> implements
        EpayMchInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String mchId = (String) params.get("mchId");
        IPage<EpayMchInfoEntity> page =
                this.page(new Query<EpayMchInfoEntity>().getPage(params),
                    new QueryWrapper<EpayMchInfoEntity>().eq(StringUtils.isNotBlank(mchId), "mch_id", mchId));

        return new PageUtils(page);
    }


    @Override
    public PageUtils queryAuditPage(Map<String, Object> params) {
        IPage<EpayMchInfoEntity> page =
                this.page(new Query<EpayMchInfoEntity>().getPage(params),
                    new QueryWrapper<EpayMchInfoEntity>().ne("audit_status", 1));
        return new PageUtils(page);
    }
}
