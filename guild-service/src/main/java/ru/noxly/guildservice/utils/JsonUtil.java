package ru.noxly.guildservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    /**
     * Преобразует объект в строку JSON.
     *
     * @param object объект для преобразования
     * @return строка JSON
     */
    public static String toJson(Object object) {
        if (object == null) {
            return "{null}";
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Ошибка при преобразовании объекта в JSON: {}", e.getMessage());
            return "{Error}";
        }
    }
}
