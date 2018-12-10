package com.intellij.cat;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Scanner;

/**
 * main class
 */
public class Main {

    static boolean running = true;

    public static void main(String[] args) {

        System.out.println("我要坐电梯...");


        Scanner scanner = new Scanner(System.in);
        while (running) {

            String next = scanner.next();

            System.out.println("next== " + next);
            if (isExit(next)) {
                running = false;
                System.out.println(" exit ...");
            }
        }
    }


    private static boolean isExit(String next) {
        next = StringUtils.strip(next);
        if (StringUtils.isNotEmpty(next)) {
            return StringUtils.equalsIgnoreCase(next, "\\q")
                    || StringUtils.equalsIgnoreCase(next, "q");
        }
        return false;
    }


}
