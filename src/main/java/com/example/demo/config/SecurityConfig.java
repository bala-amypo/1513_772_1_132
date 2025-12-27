package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**").permitAll()
            // User endpoints
            .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
            // Skill endpoints
            .antMatchers(HttpMethod.GET, "/api/skills/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/skills/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/skills/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/skills/**").hasRole("ADMIN")
            // Skill Request endpoints
            .antMatchers(HttpMethod.GET, "/api/requests/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/requests/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/requests/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/requests/**").hasRole("ADMIN")
            // Skill Offer endpoints
            .antMatchers(HttpMethod.GET, "/api/offers/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/offers/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/offers/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/offers/**").hasRole("ADMIN")
            // Match endpoints
            .antMatchers(HttpMethod.GET, "/api/match/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/api/match/**").hasRole("ADMIN")
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