//package com.example.auth_server.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class MyConfig {
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .oidc(Customizer.withDefaults());
//
//        //TODO Будущий ресурсный сервер
////        http.oauth2ResourceServer((resourceServer) -> resourceServer
////                        .jwt(Customizer.withDefaults()));
//
////        http
////                .authorizeHttpRequests((authorize) -> authorize
////                        .anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults());
//
////        http.formLogin(login -> login
////                        .defaultSuccessUrl("/")
////                        .permitAll())
////                .logout(logout -> logout
////                        .logoutSuccessUrl("/"));
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    UserDetailsManager inMemoryUserDetailsManager() {
//        var user = User.withUsername("user").password("{noop}password").build();
//        return new InMemoryUserDetailsManager(user);
//    }
//}
