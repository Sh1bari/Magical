package ru.noxly.guildservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "ru.noxly.guildservice",    // Пакет вашего сервиса
        "ru.noxly.resolver",    // Пакет библиотеки
        "ru.noxly.validation",    // Пакет библиотеки
})
public class GuildServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuildServiceApplication.class, args);
    }

}
