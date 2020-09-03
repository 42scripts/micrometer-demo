package com.demo.micrometer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class G2Service {
    private final MeterRegistry registry;

    @Getter
    private List<AtomicInteger> list;

    @Getter
    @Setter
    AtomicInteger i = new AtomicInteger(0);


    public G2Service(MeterRegistry registry){
        this.registry = registry;

/*        this.list = registry.gauge("listGauge", Collections.emptyList(), new ArrayList<>(), List::size);
        this.i = registry.gauge("numberGauge", new AtomicInteger(0));*/
        Gauge gauge = Gauge
                .builder("atomic", i, AtomicInteger::intValue )
                .register(registry);
    }

/*    @PostConstruct
    void m(){
        Gauge gauge = Gauge
                .builder("atomic", i, AtomicInteger::intValue )
                .register(registry);
    }*/
}
