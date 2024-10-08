package com.personal.patient.account.configs;

import com.personal.patient.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors(httpSecurityCorsConfigurer -> 
                        httpSecurityCorsConfigurer.configurationSource(request -> {
                            CorsConfiguration corsConfig = new CorsConfiguration();
                            corsConfig.setAllowedOrigins(Arrays.asList("https://lkpatsienta.tech","http://localhost:4200"));
                            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
                            corsConfig.setAllowedHeaders(Arrays.asList("*"));
                            return corsConfig;
                        })
                    )
                .authorizeRequests()
                .antMatchers("/secured").authenticated()
                .antMatchers("/info").authenticated()
                .antMatchers("/userinfo/save").authenticated()
                .antMatchers("/all-cards").authenticated()
                .antMatchers("/all-not-done-cards").authenticated()
                .antMatchers("/all-done-cards").authenticated()
                .antMatchers("/files/result-file/**").authenticated()
                .antMatchers("/files/passport-file/**").authenticated()
                .antMatchers("/documents/snils").authenticated()
                .antMatchers("/documents/passport").authenticated()
                .antMatchers("/documents/passport/passport-file").authenticated()
                .antMatchers("/documents/passport/passport-file/**").authenticated()
                .antMatchers("/profile").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        ;
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
