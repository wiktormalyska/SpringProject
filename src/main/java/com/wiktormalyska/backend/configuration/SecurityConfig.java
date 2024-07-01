package com.wiktormalyska.backend.configuration;

import com.wiktormalyska.backend.configuration.auth.CustomLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/actuator/**").permitAll()
                                .requestMatchers("/api/items/all").permitAll()
                                .requestMatchers("/api/items/get").permitAll()
                                .requestMatchers("/api/items/add").hasAuthority("ADMIN")
                                .requestMatchers("/api/items/remove").hasAuthority("ADMIN")
                                .requestMatchers("/api/users/**").hasAuthority("ADMIN")
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/home", "/").permitAll()
                                .requestMatchers("/cart", "/cart/**").hasAnyAuthority("USER", "ADMIN")
                                .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(new CustomLoginSuccessHandler())
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
