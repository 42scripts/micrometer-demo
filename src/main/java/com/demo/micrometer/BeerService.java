package com.demo.micrometer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    private final MeterRegistry meterRegistry;
    private Counter projectCounter;
    //private Counter aleOrderCounter;

    private List<String> projectsCountList = new ArrayList<>();

    public BeerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initOrderCounters();
        /*Counter.builder("projectCounter")
                .description("Execute project counter")
                .register(meterRegistry);*/
        Gauge.builder("projectsInQueue", projectsCountList, Collection::size)
                .description("projectsCount")
                .register(meterRegistry);
    }

    public void inc(){
        projectCounter.increment();
    }

    private void initOrderCounters() {
        projectCounter = this.meterRegistry.counter("startProjectCount"); // 1 - create a counter
/*        aleOrderCounter = Counter.builder("beer.orders")    // 2- create a counter using the fluent API
                .tag("type", "ale")
                .description("The number of orders ever placed for Ale beers")
                .register(meterRegistry);*/
    }

/*    public void orderBeer(Order order) {
        orders.add(order);

        if ("light".equals(order.type)) {
            lightOrderCounter.increment(1.0);  // 3 - increment the counters
        } else if ("ale".equals(order.type)) {
            aleOrderCounter.increment();
        }
    }*/

/*    @Scheduled(fixedRate = 5000)
    @Timed(description = "Time spent serving orders", longTask = true)
    public void serveFirstOrder() throws InterruptedException {
        if (!orders.isEmpty()) {
            Order order = orders.remove(0);
            Thread.sleep(1000L * order.amount);
        }
    }*/
/*}

class Order {
    int amount;
    String type;

    public Order(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }*/
}
