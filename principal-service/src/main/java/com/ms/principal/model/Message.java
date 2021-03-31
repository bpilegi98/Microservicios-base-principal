package com.ms.principal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Configuration
@ConfigurationProperties(prefix="message")
@PropertySource("classpath:application.yml")
public class Message {

    private String message;

}