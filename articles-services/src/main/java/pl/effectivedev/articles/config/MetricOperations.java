package pl.effectivedev.articles.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class MetricOperations {
    private final MeterRegistry meterRegistry;
    private final AtomicInteger articleCount = new AtomicInteger(0);

    @PostConstruct
    public void registerGauges() {
        meterRegistry.gauge("articles_list_size", Tags.empty(), articleCount);
    }

    public void setArticleCount(int value) {
        articleCount.set(value);
    }

    public void recordDuration(String metricName, Tags tags, long time, TimeUnit timeUnit) {
        meterRegistry.timer(metricName, tags).record(time, timeUnit);
    }

    public Counter counter(String metricName, Tags tags) {
        return meterRegistry.counter(metricName, tags);
    }

    public void increment(String metricName, Tags tags) {
        counter(metricName, tags).increment();
    }


}
