package nl.jtosti.hermes.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageServiceInterface {
    public Path store(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public void init();
}
