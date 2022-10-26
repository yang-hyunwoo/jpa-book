package com.example.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.user")
@EntityScan(basePackages = {
        "com.example.user.repository"
})
public class UserAdminModule {
}
