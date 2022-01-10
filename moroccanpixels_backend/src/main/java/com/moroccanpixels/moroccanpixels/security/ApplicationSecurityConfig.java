//package com.moroccanpixels.moroccanpixels.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import static com.moroccanpixels.moroccanpixels.security.ApplicationUserRole.*;
//
//@Configuration
////@EnableWebSecurity
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/api/**").hasRole(ADMIN.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//
//    }
//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails adminUser = User.builder()
//                .username("hamza")
//                .password(passwordEncoder.encode("hamza"))
//                .roles(ADMIN.name())
//                .build();
//        UserDetails clientUser = User.builder()
//                .username("mohamed")
//                .password(passwordEncoder.encode("password"))
//                .roles(CLIENT.name())
//                .build();
//        return new InMemoryUserDetailsManager(adminUser,clientUser);
//    }
//}
