package org.document.inventory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "org.document")
@EnableJpaRepositories({
		"org.document.category.repository",
		"org.document.product.repository",
		"org.document.user.repository"
})
@EntityScan({
		"org.document.common",
		"org.document.category",
		"org.document.product",
		"org.document.user"
})
public class InventoryApp {
	public static void main(String[] args) {
		SpringApplication.run(InventoryApp.class, args);
	}

	@Configuration
	static class CORSConfig implements WebMvcConfigurer {
		@Value("${cors-allowed-origins}")
		private String corsAllowedOrigins = "";

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			String[] allowedOrigins = corsAllowedOrigins.split(" ");
			registry.addMapping("/**").allowedOrigins(allowedOrigins);
		}
	}
}
