package com.efe.ms.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 
 * <p>产品服务: </p> 
 * @author Liu TianLong
 * 2018年8月24日 上午11:42:30
 */
@SpringCloudApplication
public class ProductServiceApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
