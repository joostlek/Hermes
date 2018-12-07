package nl.jtosti.hermes.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService implements StorageServiceInterface {
    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public String store(MultipartFile file) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            String fileName = Long.toString(Math.round(Math.random() * 1000000));
            Path path = this.rootLocation.resolve(fileName + ".png");
            BufferedImage image = ImageIO.read(file.getInputStream());
            ImageIO.write(image, "png", stream);
            Files.copy(new ByteArrayInputStream(stream.toByteArray()), path);
            return fileName + ".png";
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
