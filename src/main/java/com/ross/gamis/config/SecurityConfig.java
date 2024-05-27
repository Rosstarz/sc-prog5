package com.ross.gamis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            auths -> auths
                    .requestMatchers(regexMatcher("^/(404|games|games/.+|developers|developers/.+|stores|stores/.+|game-csv)$"),
                            regexMatcher(HttpMethod.GET, "^/login\\?.*"),
                            regexMatcher(HttpMethod.GET, "^/error"))
                        .permitAll()
                    .requestMatchers(
                            antMatcher(HttpMethod.GET, "/js/**"),
                            antMatcher(HttpMethod.GET, "/css/**"),
                            antMatcher(HttpMethod.GET, "/images/**"),
                            antMatcher(HttpMethod.GET, "/webjars/**"),
                            regexMatcher(HttpMethod.GET, "\\.ico$"))
                        .permitAll()
                    .requestMatchers(
                            antMatcher(HttpMethod.GET, "/api/games/**"),
                            // antMatcher(HttpMethod.POST, "/api/games/**"), // testing
                            antMatcher(HttpMethod.GET, "/api/developers/**")
                            // )
                            ,
                            antMatcher(HttpMethod.POST, "/api/developers/**")) // Allowed for client application
                        .permitAll()
                    .requestMatchers(antMatcher(HttpMethod.GET, "/"))
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                        // .permitAll()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers(
                    // antMatcher(HttpMethod.POST, "/api/games/**"), // testing
                    antMatcher(HttpMethod.POST, "/api/developers/**") // Disabled for client application
            ))
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll())
            .exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(
                    (request, response, exception) -> {
                        if (request.getRequestURI().startsWith("/api")) {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        } else if (request.getRequestURI().startsWith("/error")) {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        } else {
                            response.sendRedirect(request.getContextPath() + "/login");
                        }
                    })
            );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
