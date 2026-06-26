//package ru.otus.currencyservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//
//
///**
// * Author: Hamdamboy
// * Date: 24.06.2026
// * Time: 11:36
// * Project: currency-service
// */
//@Configuration
//@EnableWebSecurity
//public class CORSFilter {
//
//    private final JwtAuthConverter jwtAuthConverter;
//
//    public CORSFilter(JwtAuthConverter jwtAuthConverter) {
//        this.jwtAuthConverter = jwtAuthConverter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET).permitAll()
//                        .requestMatchers(HttpMethod.POST).permitAll()
//                        .requestMatchers(HttpMethod.PATCH).permitAll()
//                        .requestMatchers(HttpMethod.PUT).permitAll()
//                        .anyRequest().authenticated()  // Secure all other endpoints
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
//                        jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter)))
//                .csrf(AbstractHttpConfigurer::disable); // CSRF should be disabled only if needed
//
//        return http.build();
//    }
//}