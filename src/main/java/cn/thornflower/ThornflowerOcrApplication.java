package cn.thornflower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.thornflower.mapper")
@SpringBootApplication
public class ThornflowerOcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThornflowerOcrApplication.class, args);
	}

}
