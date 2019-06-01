package org.epay.dal.dao.mapper;


import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 云管理平台登录
     * 2019年4月26日09:14:57
     * @return
     */
	User selectUserByloginActAndloginPwd(@Param("loginAct") String loginAct,
                                         @Param("password") String loginPwd);

    /**
     * 根据登录名查询用户
     * @param login_name
     * @return
     */
    User selectUserByLoginName(String login_name);

    /**
     * 更新密钥
     * @param loginAct
     * @param loginPwd
     * @param req_key
     * @param res_key
     * @return
     */
    int updateKeyByLoginActAndLoginPwd(@Param("loginAct") String loginAct,
                                       @Param("loginPwd") String loginPwd,
                                       @Param("req_key") String req_key,
                                       @Param("res_key") String res_key);

    /**
     * 更新密码
     * @param loginName
     * @param password
     * @param new_password
     * @return
     */
    int updatePasswordByLoginNameAndLoginPassword(@Param("loginName") String loginName,
                                                  @Param("password") String password,
                                                  @Param("new_password") String new_password);

    /**
     * 更新密码
     * @param mch_id
     * @param newLoginPwd
     * @return
     */
    int updatePassword(@Param("mch_id") String mch_id,
                       @Param("newLoginPwd") String newLoginPwd);

}