package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/", "/error", "/favicon.ico", "/api-docs-url").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs",
                    "/v3/api-docs", "/v3/api-docs/**", "/api-docs", "/api-docs/**",
                    "/webjars/**", "/configuration/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/skills/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/skills/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/skills/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/skills/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/skill-requests/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/skill-requests/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/skill-requests/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/skill-requests/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/skill-offers/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/skill-offers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/skill-offers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/skill-offers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/match-records/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/match-records/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/match-records/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/match-records/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .headers().frameOptions().disable();
        
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin@example.com")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN")
            .and()
            .withUser("user@example.com")
            .password(passwordEncoder().encode("user123"))
            .roles("USER");
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}