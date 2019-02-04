package nl.jtosti.hermes.image;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageServiceInterface {
    String store(MultipartFile file);

    Resource load(String filename);

    void deleteAll();

    void init();
}
