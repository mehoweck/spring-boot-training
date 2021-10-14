package pl.effectivedev.articles.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MetricsConfig {

    @Value("${HOSTNAME:local}")
    private String hostname;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricCommonTags() {
        return registry -> registry.config()
                .commonTags(
                        "application", "articles-service",
                        "hostname", hostname
                )
                .meterFilter(createMeterFilter());
    }

    private static MeterFilter createMeterFilter() {
        return new MeterFilter() {
            @Override
            public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                return DistributionStatisticConfig.builder()
                        .percentiles(0.50, 0.95, 0.99)
                        .build()
                        .merge(config);
            }
        };
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) { return new TimedAspect(meterRegistry); }
}
