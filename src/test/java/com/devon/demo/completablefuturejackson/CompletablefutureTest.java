package com.devon.demo.completablefuturejackson;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletablefutureTest {

    private static final Logger log = LoggerFactory.getLogger(CompletablefutureTest.class);

    @Test
    public void cf_1() {

        log.info("========Started cf_1========");

        ThreadPoolTaskExecutor executor = threadPoolTaskExecutor();

        List<Integer> listOfInt = Arrays.asList(1, 3, 5, 6, 8, 10);

        List<Integer> anotherListOfInt = Arrays.asList(11, 13, 15, 16, 18, 100);

        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();


        listOfInt.forEach(num -> {

            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {

                log.info("----{}----", num);
                runAnthorCF(anotherListOfInt);

            }, executor);
            completableFutureList.add(cf);

        });


        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[listOfInt.size()])).join();

        log.info("------Done-----");


    }


    private void runAnthorCF(List<Integer> list) {

        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
        list.forEach(num -> {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {

                    log.info("----from another cf {}----", num);


            });

            completableFutureList.add(cf);
        });

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).join();


    }


    private ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setThreadNamePrefix("myCfThread-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
