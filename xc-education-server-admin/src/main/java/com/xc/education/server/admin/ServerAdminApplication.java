/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.xc.education.server.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @author itheima
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class ServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAdminApplication.class, args);
	}

}
