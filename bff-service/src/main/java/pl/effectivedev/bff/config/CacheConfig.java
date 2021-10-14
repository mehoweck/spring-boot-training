package pl.effectivedev.bff.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    // Caffeine - in memory
    // Redis - persystencja na zewnątrz
    // Hazlecast - in memory synchronizowane pomiędzy instancjami


    @Bean
    public CacheManager cacheManager() {
        var manager = new CaffeineCacheManager();

        var cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .recordStats();

        manager.setCaffeine(cache);
        manager.setCacheNames(List.of("articles-list"));

        return manager;
    }
}
