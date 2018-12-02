package testTask.teligen.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurat {

    public Configurat() {
    }
    
   
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
