package com.demo.micrometer;

import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class G2Config {
    private final MeterRegistry registry;

    private AtomicInteger badJobCount = new AtomicInteger(0);

    public G2Config(MeterRegistry registry){
        this.registry = registry;
        Gauge gauge = Gauge
                .builder("atomic2", badJobCount, AtomicInteger::intValue )
                .register(registry);
    }

    @Bean(name = "badJobCount")
    public AtomicInteger badJobCount(MeterRegistry meterRegistry) {
        return badJobCount;
    }
}
