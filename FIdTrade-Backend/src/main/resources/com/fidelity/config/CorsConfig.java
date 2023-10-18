package com.fidelity.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins("*"); // Allow requests from any origin (you can specify a specific origin instead)
        configuration.setAllowedMethods("*"); // Allow all HTTP methods
        configuration.setAllowedHeaders("*"); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials like cookies, if needed

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply this configuration to all paths

        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource());
        return corsFilter;
    }
}



//@Configuration
//public class CorsConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/client/activityReport")
//                        .allowedOrigins("http://localhost:4200")
//                        .allowedMethods("GET", "POST"); // Add other HTTP methods as needed
//            }
//        };
//    }
//}
