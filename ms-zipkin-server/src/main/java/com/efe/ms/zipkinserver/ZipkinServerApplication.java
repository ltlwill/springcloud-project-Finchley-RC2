package com.efe.ms.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import zipkin2.server.internal.EnableZipkinServer;

/**
 * 
 * <p>zipkin链路跟踪服务: </p> 
 * @author Liu TianLong
 * 2018年8月29日 下午2:49:25
 */

@EnableZipkinServer
@SpringCloudApplication
public class ZipkinServerApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
