package ru.geekbrains;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@EnableFeignClients
@Configuration
public class FeignConfig {
}
