package com.cts.FuelDrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FuelDropApplication {

//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		FilterRegistrationBean fb = new FilterRegistrationBean();
//		fb.setFilter(new JWTFilter());
//		fb.addUrlPatterns("/api/v1/*");
//		return fb;
//	}

	public static void main(String[] args) {
		SpringApplication.run(FuelDropApplication.class, args);
	}

}
