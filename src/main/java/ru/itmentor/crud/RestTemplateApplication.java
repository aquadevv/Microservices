package ru.itmentor.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmentor.crud.service.UserService;

@Slf4j
@SpringBootApplication
public class RestTemplateApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RestTemplateApplication.class, args);
        UserService userService = context.getBean(UserService.class);

        String finalCode = userService.executeTask();
        log.info("Final Code: {}", finalCode);

        context.close();
    }
}
