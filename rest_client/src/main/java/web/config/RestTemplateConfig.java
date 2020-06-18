/*package web.config;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        return restTemplateBuilder
                .basicAuthentication("ADMIN", "ADMIN")

                .build();
    }
   /* @Bean
    public HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            String auth = "ADMIN:ADMIN";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}*/
