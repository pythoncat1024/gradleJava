package com.intellij.cat;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * main class
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("hello world");


        System.out.println("xxx");

        Flowable.range(1, 5)
                .subscribe(index -> {
                    System.out.println("index == " + index);
                });

        new Thread(() -> {

            Flowable.interval(0, 2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.computation())
                    .doOnComplete(() -> System.out.println("hello duck"))
                    .take(5)
                    .doOnSubscribe(t -> {
                        System.err.println("interval: " + t);
                    })
                    .subscribe();

            Observable.interval(100, TimeUnit.MICROSECONDS)
                    .subscribe(tt -> System.out.println("tt == " + tt));
        }).start();
    }

}
