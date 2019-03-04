package lab.togo.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //ClientController类第3种方式需要使用这个注解
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
