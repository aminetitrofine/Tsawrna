package com.moroccanpixels.moroccanpixels.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User hamza = new User(
                    "benyazidhamza",
                    "benyazidhamza969@gmail.com",
                    "15032000",
                    RoleType.ADMIN,
                    StatusType.CONFIRMED
            );
            User amine = new User(
                    "amine",
                    "aminetitro@gmail.com",
                    "password",
                    RoleType.CONTRIBUTOR,
                    StatusType.ARCHIVED
            );
            userRepository.saveAll(List.of(hamza,amine));
        };
    }
}
