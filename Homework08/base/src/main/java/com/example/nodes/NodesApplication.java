package com.example.nodes;

import com.example.nodes.service.NodeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class NodesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodesApplication.class, args);
	}

}
