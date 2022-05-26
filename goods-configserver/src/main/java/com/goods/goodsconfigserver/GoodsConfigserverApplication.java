package com.goods.goodsconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GoodsConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsConfigserverApplication.class, args);
	}

}
