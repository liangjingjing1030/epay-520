package org.epay;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description: epay支付核心服务,包括:各支付渠道接口,通知处理
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.epay.org
 */
//@ComponentScan(value = {
//"",
//""
//})
@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
public class EpayServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EpayServiceApplication.class).web(true).run(args);
	}

}
