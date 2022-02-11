package com.falcione.nic.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            List<Student> students = List.of(new Student("Nic", LocalDate.of(1995, 7, 30), "myemail@gmail.com"),
                    new Student("John", LocalDate.now(), "jonsemail@gmail.com"));

            studentRepository.saveAll(students);
        };
    }
}
