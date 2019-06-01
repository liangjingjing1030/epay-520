package org.epay.mgr.service.impl;

import org.epay.dal.dao.mapper.*;
import org.epay.dal.dao.model.*;
import org.epay.mgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CertificateTypeMapper certificateTypeMapper;

    @Autowired
    private ProvinceCityMapper provinceCityMapper;

    @Autowired
    private BusinessRangeMapper businessRangeMapper;

    @Autowired
    private MchInfoMapper mchInfoMapper;

    @Override
    public User login(String loginAct, String loginPwd) {
        return userMapper.selectUserByloginActAndloginPwd(loginAct, loginPwd);
    }

    @Override
    public User queryByLoginNameAndPassword(String loginAct, String loginPwd) {
        // 根据用户账号和登录密码查询用户信息
        User user = userMapper.selectUserByloginActAndloginPwd(loginAct,loginPwd);

        return user;
    }

    /**
     * 查询所有证件类型
     * @return
     */
    public List<CertificateType> queryAllCertificateType() {
        return certificateTypeMapper.selectAll();
    }

    /**
     * 查询所有省份
     * @return
     */
    public List<ProvinceCity> queryProvince() {
        return provinceCityMapper.selectAllProvince();
    }

    /**
     * 更新密钥
     *
     * @param loginAct
     * @param loginPwd
     * @param req_key
     * @param res_key
     * @return
     */
    @Override
    public int updateUserByReqKeyAndResKey(String loginAct, String loginPwd, String req_key, String res_key) {
        return userMapper.updateKeyByLoginActAndLoginPwd(loginAct, loginPwd, req_key, res_key);
    }

    /**
     * 查询私钥
     * @return
     */
    @Override
    public List<User> queryKey(String loginName, String password) {
        List<User> userList = new ArrayList<>();
        User user = userMapper.selectUserByloginActAndloginPwd(loginName, password);
        userList.add(user);

        return userList;
    }

    /**
     * 更新密码
     * @param loginName
     * @param password
     * @param new_password
     * @return
     */
    @Override
    public int updatePasswordByLoginNameAndLoginPassword(String loginName, String password, String new_password) {
        return userMapper.updatePasswordByLoginNameAndLoginPassword(loginName, password, new_password);
    }

    @Override
    public List<BusinessRange> queryAllBusinessRange() {
        return businessRangeMapper.selectAllBusinessRange();
    }

    @Override
    public MchInfo queryMchInfoByMchId(String mch_id) {
        return mchInfoMapper.selectByPrimaryKey(mch_id);
    }

    /**
     * 根据父id查询查询子对象
     * @param id
     * @return
     */
    public List<ProvinceCity> queryCityByParentId(int id) {
        return provinceCityMapper.selectByFatherId(id);
    }

}
