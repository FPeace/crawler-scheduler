package com.mtl.crawler.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * scheduler服务进程启动
 *
 * @author ZhangFeng
 * @since 2020/01/06
 */
@SpringBootApplication(scanBasePackages = {"com.mtl.crawler"},exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.mtl.crawler" )
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }
}
