package com.hendisantika.springbootdatatable;

import com.hendisantika.springbootdatatable.domain.User;
import com.hendisantika.springbootdatatable.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@SpringBootApplication
public class SpringBootDatatableApplication {
    @Autowired
    UserRepository userRepository;
    private Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDatatableApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository repository) {
        return (args) -> {
            // save a couple of users
            repository.save(new User("Naruto", "12,000"));
            repository.save(new User("Sasuke", "12,000"));
            repository.save(new User("Sakura", "12,000"));
            repository.save(new User("Kakashi", "12,000"));
            repository.save(new User("Neji", "12,000"));
            repository.save(new User("Kiba", "12,000"));
            repository.save(new User("Hinata", "12,000"));
            repository.save(new User("Shikamaru", "12,000"));
            repository.save(new User("Choji", "12,000"));
            repository.save(new User("Ten-ten", "12,000"));
            repository.save(new User("Lee", "12,000"));
            repository.save(new User("Shino", "12,000"));
            repository.save(new User("Ino", "12,000"));
            repository.save(new User("Gaara", "12,000"));
            // fetch all users
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<User> user = repository.findById(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(user.toString());
            log.info("");

            // fetch users by last name
            log.info("Customer found with findByNameIgnoreCase('naruto'):");
            log.info("--------------------------------------------");
            log.info("Result -->  " + repository.findByNameIgnoreCase("naruto"));
            log.info("");
        };
    }
}
