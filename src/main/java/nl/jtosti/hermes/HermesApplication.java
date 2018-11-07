package nl.jtosti.hermes;

import javafx.application.Application;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HermesApplication {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(HermesApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(UserRepository repository) {
	    return (args) -> {
	        repository.save(new User("Joost", "Lekkerkerker"));
	        for (User user : repository.findAll()) {
	            log.info(user.toString());
            }
            for (User user : repository.findByLastName("Lekkerkerker")) {
                log.info(user.toString());
            }
        };
    }
}
