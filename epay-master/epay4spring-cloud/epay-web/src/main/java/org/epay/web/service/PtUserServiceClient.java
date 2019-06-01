package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 商户登陆信息client
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@Service
public class PtUserServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据登录名查询商户信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "selectPtUserByLoginNameFallback")
    public String selectPtUserByLoginName(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE2/pt_user/selectPtUserByLoginName?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String selectPtUserByLoginNameFallback(String jsonParam) {
        return "error";
    }
    
    /**
     * 添加商户登陆信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "addIntoPtUserFallback")
    public String addIntoPtUser(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE2/pt_user/addIntoPtUser?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String addIntoPtUserFallback(String jsonParam) {
        return "error";
    }

    /**
     * 更新密码
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "updatePasswordFallback")
    public String updatePassword(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE2/pt_user/updatePassword?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String updatePasswordFallback(String jsonParam) {
        return "error";
    }

}