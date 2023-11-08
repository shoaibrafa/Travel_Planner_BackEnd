package travel.planner.com.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    /**
     * The following been is used to handle cors configuration.
     * This configuration enables Spring to accept all requests
     * (PUT,GET,POST,DELETE) from localhost:4200 and for all urls.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200")
                        .allowedOrigins("http://travelplanner2.s3-website.ca-central-1.amazonaws.com")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}
