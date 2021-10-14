package pl.effectivedev.articles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"formatters", "plainFormatter"})
class ArticlesServicesApplicationTests {

	@Test
	void contextLoads() {
	}

}
