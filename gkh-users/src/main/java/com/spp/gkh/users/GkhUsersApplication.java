package com.spp.gkh.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GkhUsersApplication {

    public static void main(String[] args) {
        //SpringApplication.run(GkhUsersApplication.class, args);
        new SpringApplicationBuilder(GkhUsersApplication.class).run(args);
    }

}
