package com.efe.ms.serviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 
 * <p>
 * 服务消费应用:
 * </p>
 * 
 * @author Liu TianLong 2019年2月19日 上午10:39:23
 */
@SpringCloudApplication
public class ServiceConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerApplication.class, args);
	}
}
