package com.example.ram.config;

import com.example.ram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserRepository userRepository;

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/css/**", "/", "/page/**", "/character/**").permitAll()
			.requestMatchers("/watch/**", "/buy/**", "/confirm/**").hasAnyRole("USER")
			.anyRequest().authenticated()
		)
		.formLogin(login -> login
				.defaultSuccessUrl("/")
				.permitAll())
		.logout(logout -> logout
				.logoutSuccessUrl("/"));
        return http.build();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	UserDetailsManager inMemoryUserDetailsManager() {
		var user = User.withUsername("user").password("{noop}password").roles("USER").build();
		userRepository.save(new com.example.ram.domain.User(user.getUsername()));
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager(user);
		return detailsManager;
	}
}
