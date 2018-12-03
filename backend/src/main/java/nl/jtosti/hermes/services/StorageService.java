package nl.jtosti.hermes.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService implements StorageServiceInterface {
    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public Path store(MultipartFile file) {
        try {
            Path path = this.rootLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            return path;
        } catch (Exception e) {
            throw new RuntimeException("Fail");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fail");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Fail");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Fail");
        }
    }
}
