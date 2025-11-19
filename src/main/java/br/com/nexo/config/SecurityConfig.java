package br.com.nexo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/css/**", "/images/**", "/js/**", "/login", "/acesso_negado").permitAll()
            .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/usuario/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin((login) -> login
                    .loginPage("/login")
                    .usernameParameter("email")      // mapeia o campo email
                    .passwordParameter("password")   // mantém o padrão password
                    .defaultSuccessUrl("/home", true)
                    .failureUrl("/login?falha=true")
                    .permitAll()
            )
            .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            )
            .exceptionHandling((exception) -> 
            exception.accessDeniedHandler((request, response, ex) -> {
                response.sendRedirect("/acesso_negado");
            })
            );

        return http.build();
    }
}
