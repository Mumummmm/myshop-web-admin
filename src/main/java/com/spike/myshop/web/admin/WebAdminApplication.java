package com.spike.myshop.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication(scanBasePackages = "com.spike.myshop", exclude = DataSourceAutoConfiguration.class)
@EnableHystrix
@EnableHystrixDashboard
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }
}
