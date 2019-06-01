package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.EpayAccountFileDao;
import io.renren.modules.manager.entity.EpayAccountFileEntity;
import io.renren.modules.manager.service.EpayAccountFileService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("epayAccountFileService")
public class EpayAccountFileServiceImpl extends ServiceImpl<EpayAccountFileDao, EpayAccountFileEntity>
        implements EpayAccountFileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String fileId = (String) params.get("fileId");
        IPage<EpayAccountFileEntity> page =
                this.page(new Query<EpayAccountFileEntity>().getPage(params),
                    new QueryWrapper<EpayAccountFileEntity>().eq(StringUtils.isNotBlank(fileId), "file_id",
                        fileId));

        return new PageUtils(page);
    }

}
