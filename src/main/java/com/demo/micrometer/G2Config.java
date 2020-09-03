package com.demo.micrometer;

import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class G2Config {
    private final MeterRegistry registry;

    private AtomicInteger badJobCount = new AtomicInteger(0);

    public G2Config(MeterRegistry registry){
        this.registry = registry;
        Gauge gauge = Gauge
                .builder("atomic2", badJobCount, AtomicInteger::intValue )
                .register(registry);
    }

/*    @Bean(name = "badJobCount")
    public AtomicInteger badJobCount(MeterRegistry meterRegistry) {
        return badJobCount;
    }*/

    @AfterReturning("execution(* com.demo.micrometer.metricController.startAll(*,*))")
    private void beforeCallMethodSubmit1(JoinPoint joinPoint){
        badJobCount.getAndSet(0);
    }

    @AfterThrowing("execution(* com.demo.micrometer.metricController.startAll(*,*))")
    private void beforeCallMethodSubmit2(JoinPoint joinPoint){
        badJobCount.getAndIncrement();
    }

    /*    @Bean(name = "badJobCount")
    public AtomicInteger badJobCount(MeterRegistry meterRegistry) {
        return badJobCount;
    }*/
}
