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
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/", "/login", "/logout", "/css/**", "/images/**", "/js/**", "/acesso_negado", "/cadastro", "/cadastro/**", "/usuarios/inserir", "/usuario/**", "/actuator/**", "/descricao-clientes/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/images/**", "/js/**", "/login", "/acesso_negado").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/cadastro", "/cadastro/**").permitAll()
                .requestMatchers("/usuarios/inserir").permitAll()
                .requestMatchers("/usuario/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin((login) -> login
                    .loginPage("/login")
                    .usernameParameter("email")      
                    .passwordParameter("password")   
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
