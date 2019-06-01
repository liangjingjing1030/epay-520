package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.EpayChannelInfoDao;
import io.renren.modules.manager.entity.EpayChannelInfoEntity;
import io.renren.modules.manager.service.EpayChannelInfoService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("epayChannelInfoService")
public class EpayChannelInfoServiceImpl extends ServiceImpl<EpayChannelInfoDao, EpayChannelInfoEntity>
        implements EpayChannelInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String mch_id = (String) params.get("mchId");
        IPage<EpayChannelInfoEntity> page =
                this.page(new Query<EpayChannelInfoEntity>().getPage(params),
                    new QueryWrapper<EpayChannelInfoEntity>().eq(StringUtils.isNotBlank(mch_id), "mch_id",
                        mch_id));

        return new PageUtils(page);
    }

}
