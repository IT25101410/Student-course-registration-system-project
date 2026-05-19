package com.shadow.studentcoursemanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Password Encoder (Essential for security checks)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. HARDCODED USERS (No Database Required)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // Admin User (Note that your success handler looks for "ROLE_ADMIN")
        UserDetails admin = User.withUsername("admin@sliit.lk") // This acts as the 'email'
                .password(passwordEncoder.encode("admin123"))   // Plain text password is "admin123"
                .roles("ADMIN")                                 // Spring automatically saves this as "ROLE_ADMIN"
                .build();

        // Student User (Note that your success handler looks for "ROLE_STUDENT")
        UserDetails student1 = User.withUsername("student@sliit.lk") // This acts as the 'email'
                .password(passwordEncoder.encode("student123"))     // Plain text password is "student123"
                .roles("STUDENT")                                   // Spring automatically saves this as "ROLE_STUDENT"
                .build();
        UserDetails student2 = User.withUsername("oshadakaveesh2022@gmail.com") // This acts as the 'email'
                .password(passwordEncoder.encode("Oshada2003"))     // Plain text password is "student123"
                .roles("STUDENT")                                   // Spring automatically saves this as "ROLE_STUDENT"
                .build();
        UserDetails student3 = User.withUsername("KavinduPasan123@gmail.com") // This acts as the 'email'
                .password(passwordEncoder.encode("Kavindu2003"))     // Plain text password is "student123"
                .roles("STUDENT")                                   // Spring automatically saves this as "ROLE_STUDENT"
                .build();

        return new InMemoryUserDetailsManager(admin, student1,student2,student3);
    }

    // 3. Your Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email") // Tells Spring to map your form's 'email' field to the hardcoded usernames above
                        .successHandler(new UrlAuthenticationSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
