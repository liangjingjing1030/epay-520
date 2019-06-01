package org.epay.service.service;

import org.epay.dal.dao.mapper.UserMapper;
import org.epay.dal.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名: 商户登陆信息处理
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@Component
public class PtUserService {

    @Autowired
    private UserMapper ptUserMapper;

    /**
     * 添加商户登陆信息
     * @param ptUser
     * @return
     */
    public int insertIntoPtUser(User ptUser) {
        return ptUserMapper.insertSelective(ptUser);
    }
    
    /**
     * 根据登录名查询商户信息
     * @param login_name
     * @return
     */
    public User selectPtUserByLoginName(String login_name) {
        return ptUserMapper.selectUserByLoginName(login_name);
    }

    /**
     * 更新密码
     * @param mch_id
     * @param newLoginPwd
     * @return
     */
    public int updatePassword(String mch_id, String newLoginPwd) {
        return ptUserMapper.updatePassword(mch_id, newLoginPwd);
    }
}
