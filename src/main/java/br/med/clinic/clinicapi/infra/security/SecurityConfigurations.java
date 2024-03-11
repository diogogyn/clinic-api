package br.med.clinic.clinicapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/doctor").hasAnyRole("ADMIN", "DOCTOR", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/doctor").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/doctor").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/patient").hasAnyRole("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/patient").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/patient").hasAnyRole("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.PUT, "/appointment").hasAnyRole("ADMIN", "DOCTOR", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/appointment").hasAnyRole("ADMIN","PATIENT", "USER")
                .requestMatchers(HttpMethod.POST, "/appointment").hasAnyRole("ADMIN", "PATIENT", "USER", "EMPLOYEE")
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
