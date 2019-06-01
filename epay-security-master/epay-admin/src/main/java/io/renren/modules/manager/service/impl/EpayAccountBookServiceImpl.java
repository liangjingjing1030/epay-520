package io.renren.modules.manager.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.manager.dao.EpayAccountBookDao;
import io.renren.modules.manager.entity.EpayAccountBookEntity;
import io.renren.modules.manager.service.EpayAccountBookService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("epayAccountBookService")
public class EpayAccountBookServiceImpl extends ServiceImpl<EpayAccountBookDao, EpayAccountBookEntity>
        implements EpayAccountBookService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String account_book_id = (String) params.get("accountBookId");
        IPage<EpayAccountBookEntity> page =
                this.page(new Query<EpayAccountBookEntity>().getPage(params),
                    new QueryWrapper<EpayAccountBookEntity>().eq(StringUtils.isNotBlank(account_book_id),
                        "account_book_id", account_book_id));

        return new PageUtils(page);
    }

}
