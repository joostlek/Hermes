package nl.jtosti.hermes.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageServiceInterface {
    public String store(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public void init();
}
