package org.epay.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.common.util.MySeq;
import org.epay.web.service.ChannelInfoServiceClient;
import org.epay.web.service.MchInfoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 验证创建退款订单请求参数
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@Component
@Lazy
public class RefundOrderValidateParamsUtils {

    private final MyLog _log = MyLog.getLog(RefundOrderValidateParamsUtils.class);

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;

    @Autowired
    private ChannelInfoServiceClient channelInfoServiceClient;

    /**
     * 验证创建退款订单请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @return
     */
    public Object validateParams(JSONObject params) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 支付参数
        JSONObject request_header = params.getJSONObject("request_header");
        String request_channel =  request_header.getString("request_channel");// 请求渠道
        
        JSONObject request_body = params.getJSONObject("request_body");
        String mch_id = request_body.getString("mch_id");								// 商户ID
        String pay_orderid = request_body.getString("pay_orderid");						// 支付订单号
        String user_id = request_body.getString("user_id");								// 客户唯一标识
        String user_name = request_body.getString("user_name");							// 用户名称
        String user_channel_account = request_body.getString("user_channel_account"); 	// 客户渠道账户
        String refund_amount = request_body.getString("refund_amount");					// 支付金额

        String sign = params.getString("sign")	;										// 签名
        
        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数[request_channel] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_id)) {
            errorMessage = "请求参数[mch_id] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(pay_orderid)) {
            errorMessage = "请求参数[pay_orderid] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(user_id)) {
            errorMessage = "请求参数[user_id] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(user_name)) {
            errorMessage = "请求参数[user_name] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(user_channel_account)) {
            errorMessage = "请求参数[user_channel_account] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(refund_amount)) {
            errorMessage = "请求参数[refund_amount] 为空.";
            return errorMessage;
        }
        if("MGR".equals(request_channel)) {
        	// 签名信息
            if (StringUtils.isBlank(sign)) {
                errorMessage = "请求参数[sign] 为空.";
                return errorMessage;
            }
        }
        
        // 查询商户信息
        JSONObject mchInfo;
        String retStr = mchInfoServiceClient.selectMchInfoByMchId(getJsonParam("mch_id", mch_id));

        JSONObject retObj = JSON.parseObject(retStr);
        if("0000".equals(retObj.getString("code"))) {
            mchInfo = retObj.getJSONObject("result");
            if (mchInfo == null) {
                errorMessage = "未查询到[mch_id="+mch_id+"] 的商户信息 in db.";
                return errorMessage;
            }
            if(mchInfo.getByte("mch_status") != 1) {
                errorMessage = "商户号为 [mch_id="+mch_id+"] 的商户未启用 in db.";
                return errorMessage;
            }
        }else {
            errorMessage = "未查询到[mch_id="+mch_id+"] 的商户信息 in db.";
            _log.info("查询商户没有正常返回数据,code={},msg={}", retObj.getString("code"), retObj.getString("msg"));
            return errorMessage;
        }
        
        // 查询商户对应的支付渠道
//        JSONObject channelInfo;
//        retStr = channelInfoServiceClient.selectChannelInfoByChannelIdAndMchId(getJsonParam(new String[]{"channel_id", "mch_id"}, new String[]{request_channel, mch_id}));
//        retObj = JSON.parseObject(retStr);
//        if("0000".equals(retObj.getString("code"))) {
//        	channelInfo = JSON.parseObject(retObj.getString("result"));
//            if(channelInfo == null) {
//                errorMessage = "Can't found payChannel[channel_id="+request_channel+",mch_id="+mch_id+"] record in db.";
//                return errorMessage;
//            }
//            if(channelInfo.getByte("channel_status") != 1) {
//                errorMessage = "channel not available [channel_id="+request_channel+",mch_id="+mch_id+"]";
//                return errorMessage;
//            }
//        }else {
//            errorMessage = "Can't found payChannel[channel_id="+request_channel+",mch_id="+mch_id+"] record in db.";
//            _log.info("查询渠道没有正常返回数据,code={},msg={}", retObj.getString("code"), retObj.getString("msg"));
//            return errorMessage;
//        }
//        String channel_mchId = channelInfo.getString("channel_mchId");
        
        // 验证参数通过,返回JSONObject对象pay_orderid   refund_amount
        JSONObject refundOrder = new JSONObject();
        refundOrder.put("refund_order_id", MySeq.getRefund());
        refundOrder.put("pay_orderid", pay_orderid);
        refundOrder.put("mch_id", mch_id);
        refundOrder.put("channel_id", request_channel);
        refundOrder.put("user_id", user_id);
        refundOrder.put("user_name", user_name);
        refundOrder.put("user_channel_account", user_channel_account);
        refundOrder.put("refund_amount", Long.parseLong(refund_amount));
        refundOrder.put("status", (byte)0);
        refundOrder.put("channel_mch_id", "");//商户识别码
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	String myDate = format.format(new Date());
    	refundOrder.put("create_time", myDate);
        
        if("MGR".equals(request_channel)) {
        	//商户请求私钥
            String req_key = mchInfo.getString("req_key");
            if (StringUtils.isBlank(req_key)) {
                errorMessage = "req_key is null[mch_id="+mch_id+"] record in db.";
                return errorMessage;
            }
            // 验证签名数据
            boolean verifyFlag = EPayUtil.verifyPaySign(params, req_key);
            if(!verifyFlag) {
                errorMessage = "验证签名信息失败.";
                return errorMessage;
            }
            refundOrder.put("res_key", mchInfo.getString("res_key"));//商户响应私钥
        }
        
        return refundOrder;
    }

    String getJsonParam(String[] names, Object[] values) {
        JSONObject jsonParam = new JSONObject();
        for (int i = 0; i < names.length; i++) {
            jsonParam.put(names[i], values[i]);
        }
        return jsonParam.toJSONString();
    }

    String getJsonParam(String name, Object value) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put(name, value);
        return jsonParam.toJSONString();
    }
    
	public static void main(String[] args) {
		String a = "1";
		Byte b = Byte.valueOf(a);
		System.out.println(b);
	}
}
