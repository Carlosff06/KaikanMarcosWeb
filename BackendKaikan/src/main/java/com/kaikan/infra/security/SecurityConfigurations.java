package com.kaikan.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kaikan.infra.config.CustomAccesDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    private final SecurityFilter securityFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomAccesDeniedHandler accesDeniedHandler;

    public SecurityConfigurations(SecurityFilter securityFilter, UserDetailsServiceImpl userDetailsService, CustomAccesDeniedHandler accesDeniedHandler) {
        this.securityFilter = securityFilter;
        this.userDetailsService = userDetailsService;
        this.accesDeniedHandler = accesDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req ->
                                req.requestMatchers("/api/login/**", "/api/register/**", "/", "/home/**")
                                        .permitAll()
                                        .requestMatchers(request -> request.getRequestURI().endsWith(".html")).permitAll()
                                        .requestMatchers(request -> request.getRequestURI().endsWith(".css")).permitAll()
                                        .requestMatchers(request -> request.getRequestURI().endsWith(".ico")).permitAll()
                                        .requestMatchers(request -> request.getRequestURI().endsWith(".jpg")).permitAll()
                                        .requestMatchers(request -> request.getRequestURI().endsWith(".js")).permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/categoria").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/platos/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/pedido/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/platos/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/api/usuario/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/api/usuario/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/api/usuario/**").hasRole("ADMIN")
                                        .requestMatchers("/api/**").authenticated()
                                        .anyRequest()
                                        .authenticated()

                ).userDetailsService(userDetailsService)
                .exceptionHandling
                        (e -> e.accessDeniedHandler(accesDeniedHandler)
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(
                        sm->sm
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Le indicamos a Spring el tipo de sesion
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
