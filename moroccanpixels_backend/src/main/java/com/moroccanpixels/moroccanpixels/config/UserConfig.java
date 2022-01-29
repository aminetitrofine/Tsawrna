package com.moroccanpixels.moroccanpixels.config;

import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.model.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User hamza = new User(
                    "benyazidhamza",
                    "benyazidhamza969@gmail.com",
                    passwordEncoder.encode("12345678"),
                    ApplicationUserRole.ADMIN,
                    StatusType.CONFIRMED
            );
            User amine = new User(
                    "amine",
                    "aminetitro@gmail.com",
                    passwordEncoder.encode("password"),
                    ApplicationUserRole.CONTRIBUTOR,
                    StatusType.ARCHIVED
            );
            userRepository.saveAll(List.of(hamza,amine));
        };
    }
}
