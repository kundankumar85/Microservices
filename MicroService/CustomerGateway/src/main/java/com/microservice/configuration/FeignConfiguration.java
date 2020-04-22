package com.microservice.configuration;


import feign.Logger;
import feign.Request;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.util.Arrays;

/**
 * The type Feign configuration.
 */
@Configuration
public class FeignConfiguration {

    /** Total maximum number of connections. */
    @Value("${apacheHttpClient.maxConnections:10}")
    private Integer maxConnections;

    /** Maximum number of connections per host. */
    @Value("${apacheHttpClient.maxConnectionsPerHost:10}")
    private Integer maxConnectionsPerHost;

    @Value("${apacheHttpClient.connectTimeoutMillis:2000}")
    private Integer connectTimeoutMillis;

    @Value("${apacheHttpClient.readTimeoutMillis:60000}")
    private Integer readTimeoutMillis;

    @Value("${apacheHttpClient.logLevel:Full}")
    private String logLevel;


    /**
     * Provide Feign with a Apache HttpClient instance configured with the right
     * number of connections per host (default is only 2)
     *
     * @return HttpClient http client
     */
    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create().setMaxConnPerRoute(maxConnectionsPerHost).setMaxConnTotal(maxConnections).build();
    }

    /**
     * Feign logger level logger . level.
     *
     * @return the logger . level
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Arrays.stream(Logger.Level.values()).filter(level -> level.toString().equalsIgnoreCase(logLevel)).findFirst()
            .orElse(Logger.Level.NONE);
    }

    /**
     * Options for feign client.
     *
     * @return the request. options
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeoutMillis, readTimeoutMillis);
    }

    /**
     * Local date feign formatter registrar feign formatter registrar.
     *
     * @return the feign formatter registrar
     */
    @Bean
    public FeignFormatterRegistrar localDateFeignFormatterRegistrar() {
        return formatterRegistry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(formatterRegistry);
        };
    }

}
