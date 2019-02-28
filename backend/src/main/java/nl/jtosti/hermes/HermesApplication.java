package nl.jtosti.hermes;

import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.security.Argon2PasswordEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * The start of the application
 */
@SpringBootApplication()
public class HermesApplication implements CommandLineRunner {

    @Resource
    StorageServiceInterface storageService;

    public static void main(String[] args) {
        SpringApplication.run(HermesApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    /**
     * Initializes the storage for images
     *
     * @param args arguments
     * @throws Exception because @{CommandLineRunner}
     */
    @Override
    public void run(String... args) throws Exception {
        storageService.deleteCache();
        storageService.init();
    }
}
