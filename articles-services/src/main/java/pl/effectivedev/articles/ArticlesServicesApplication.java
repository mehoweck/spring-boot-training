package pl.effectivedev.articles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableJpaRepositories
public class ArticlesServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticlesServicesApplication.class, args);
	}

}
