package com.ctran79.clinic.backend;

import com.ctran79.clinic.backend.service.Utils;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(Utils.DATE_TIME_FORMAT);
            builder.serializers(new LocalDateSerializer(Utils.DATE_FORMATTER));
            builder.serializers(new LocalDateTimeSerializer(Utils.DATE_TIME_FORMATTER));
            builder.deserializers(new LocalDateDeserializer(Utils.DATE_FORMATTER));
            builder.deserializers(new LocalDateTimeDeserializer(Utils.DATE_TIME_FORMATTER));
        };
    }
}
