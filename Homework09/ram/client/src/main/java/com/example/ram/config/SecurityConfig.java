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

/**
 * Репозиторий пользователей
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserRepository userRepository;

	/**
	 * Фильтр безопасности
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/ram/css/**", "/css/**", "/ram/", "/ram/page/**",
					"/ram/character/**").permitAll()
			.requestMatchers("/ram/watch/**", "/ram/buy/**", "/ram/confirm/**").hasAnyRole("USER")
			.anyRequest().authenticated()
		)
		.formLogin(login -> login
				.defaultSuccessUrl("/ram/")
				.permitAll())
		.logout(logout -> logout
				.logoutSuccessUrl("/ram/"));
        return http.build();
    }

	/**
	 * Кодировщик паролей
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * Менеджер пользовательских данных
	 * @return
	 */
	@Bean
	UserDetailsManager inMemoryUserDetailsManager() {
		var user = User.withUsername("user").password("{noop}password").roles("USER").build();
		userRepository.save(new com.example.ram.domain.User(user.getUsername()));
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager(user);
		return detailsManager;
	}
}
