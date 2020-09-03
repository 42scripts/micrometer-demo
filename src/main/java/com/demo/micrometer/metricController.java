package com.demo.micrometer;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/internal")
@RequiredArgsConstructor
public class metricController {
    //private final BeerService beerService;
    //private final G2Service gService;
    //@Autowired
    private final AtomicInteger badJobCount;

    @GetMapping("/submit")
    public void startAll() {
        log.info("Started");
        //beerService.orderBeer(new Order(2,"light"));
        //beerService.inc();
        //gService.getList().add("str1");
        //gService.getList2().add("STR2");
        //gService.getMap().put("key", 11234);
        //gService.getI().getAndIncrement();
        badJobCount.getAndIncrement();
    }

    @GetMapping("/clear")
    public void clearAll() {
        log.info("Clear");
        //beerService.orderBeer(new Order(2,"light"));
        //beerService.inc();
        //gService.getList().clear();
        //gService.getList2().clear();
        //gService.getMap().put("key", 11234);
        //gService.getI().getAndSet(0);
        badJobCount.getAndSet(0);
    }

}
/*projectstatuseventsender
scheduled-project-status
in application.yml wrapper*/
