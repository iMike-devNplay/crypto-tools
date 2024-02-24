package fr.home.mikedev.cryptotools.dtos.security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig 
{
    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://www.mikedev.fr", "https://mikedev.fr"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
    
    @Bean
    public SecurityFilterChain basicFilterChain(HttpSecurity http) throws Exception 
    {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .securityMatcher("/api/**")
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()); //.permitAll());
        return http.build();
    }
}
