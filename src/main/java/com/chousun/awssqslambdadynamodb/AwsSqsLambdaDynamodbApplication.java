package com.chousun.awssqslambdadynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class AwsSqsLambdaDynamodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsLambdaDynamodbApplication.class, args);
	}

}
