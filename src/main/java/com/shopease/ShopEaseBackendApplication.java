package com.shopease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ShopEaseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopEaseBackendApplication.class, args);
	}

}
