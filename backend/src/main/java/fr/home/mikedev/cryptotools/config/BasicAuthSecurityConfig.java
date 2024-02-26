package fr.home.mikedev.cryptotools.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig 
{
    CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern(CorsConfiguration.ALL);
        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }  
    
    @Bean
    public SecurityFilterChain basicFilterChain(HttpSecurity http) throws Exception 
    {
        http.securityMatcher("/api/**")
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()) //.permitAll());  .authenticated());
            .httpBasic(Customizer.withDefaults())
        	.csrf(csrf -> csrf.disable())
        	.cors(cors -> cors.configurationSource(this.corsConfigurationSource()))
        	.headers(header -> header.disable());
        return http.build();
    }
}
