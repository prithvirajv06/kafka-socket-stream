package com.prithvi.userService;

import com.prithvi.userService.dal.UserAndStockRepository;
import com.prithvi.userService.model.UserStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.ArrayList;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	UserAndStockRepository userAndStockRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserStock userStock = UserStock.builder().userId("USER123").stockList(new ArrayList<>()).build();
		userAndStockRepository.save(userStock);
	}
}
