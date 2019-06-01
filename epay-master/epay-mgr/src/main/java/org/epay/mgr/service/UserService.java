package org.epay.mgr.service;

import org.epay.dal.dao.model.*;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @param loginAct
     * @param loginPwd
     * @return
     */
    User login(String loginAct, String loginPwd);

    /**
     * 根据用户账号和登录密码查询用户信息
     * @param loginAct
     * @param loginPwd
     * @return
     */
    User queryByLoginNameAndPassword(String loginAct, String loginPwd);

    List<CertificateType> queryAllCertificateType();

    List<ProvinceCity> queryCityByParentId(int parseInt);

    List<ProvinceCity> queryProvince();

    /**
     * 注册商户后返回密钥，更新保存
     *
     * @param loginAct
     * @param loginPwd
     * @param req_key
     * @param res_key
     * @return
     */
    int updateUserByReqKeyAndResKey(String loginAct, String loginPwd, String req_key, String res_key);

    /**
     * 查询私钥
     * @return
     */
    List<User> queryKey(String loginName, String password);

    /**
     * 更新密码
     * @param loginName
     * @param password
     * @param new_password
     * @return
     */
    int updatePasswordByLoginNameAndLoginPassword(String loginName, String password, String new_password);

    /**
     * 查询所有经营范围
     * @return
     */
    List<BusinessRange> queryAllBusinessRange();

    /**
     * 根据主键查询
     * @param loginAct
     * @return
     */
    MchInfo queryMchInfoByMchId(String loginAct);
}
