package com.ncs.springBootPractice.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student lewis = new Student("lewis",  LocalDate.of(1992, 1, 17), "lewis@gmail.com");
            Student kyle = new Student("Kyle",  LocalDate.of(1995, 1, 17), "kyle@gmail.com");
            studentRepository.saveAll(
                    List.of(lewis, kyle)
            );
        };
    }
}
