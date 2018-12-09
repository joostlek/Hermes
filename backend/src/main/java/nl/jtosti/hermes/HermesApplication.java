package nl.jtosti.hermes;

import nl.jtosti.hermes.services.StorageServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

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

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
