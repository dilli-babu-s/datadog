package com.datadog.demo.config;

import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DogStatsDConfig {
        @Bean
        public StatsDClient statsDClient() {
            return new NonBlockingStatsDClientBuilder()
                    .prefix("e5")
                    .hostname("datadog-agent")
                    .port(8125)
                    .build();
        }
}
