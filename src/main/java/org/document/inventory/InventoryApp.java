package org.document.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
}
