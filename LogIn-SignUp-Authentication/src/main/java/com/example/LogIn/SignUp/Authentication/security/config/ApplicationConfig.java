package com.example.LogIn.SignUp.Authentication.security.config;


import com.example.LogIn.SignUp.Authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {
    private final UserRepository userRepository;



    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            log.info("Loading user by username: {}", username);
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> {
                        log.error("User not found: {}", username);
                        return new UsernameNotFoundException("User not found");
                    });
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        log.info("Configured DaoAuthenticationProvider");
        return authenticationProvider;
    }

//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        log.info("Configured AuthenticationManager");
//        return configuration.getAuthenticationManager();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Configured BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        log.info("Configured RestTemplate");
        return new RestTemplate();
    }
}