package com.deon.iqpublisher;

import com.deon.iqpublisher.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {
private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        PublishService publishService = new PublishService("localhost");
        boolean loop = true;

        System.out.println("  (Press [CTRL+c] to exit)");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (loop) {
                System.out.println("Enter your name.");
                String name = in.readLine();
                if (name == "") {
                    System.out.println("Name must not be empty");
                } else {
                    publishService.send(name, "IQQueue");
                }
            }
        } catch (Exception e) {
            logger.error("Error", e);
        }



    }

}
