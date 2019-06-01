package org.epay.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: epay服务注册中心
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.epay.org
 */
@EnableEurekaServer
@SpringBootApplication
public class EpayServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EpayServerApplication.class).web(true).run(args);
	}

}
