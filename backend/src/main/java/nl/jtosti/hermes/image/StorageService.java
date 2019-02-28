package nl.jtosti.hermes.image;

import nl.jtosti.hermes.config.ImagePathConfiguration;
import nl.jtosti.hermes.image.exception.FileStoreException;
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
import java.util.Date;

@Service
public class StorageService implements StorageServiceInterface {

    private Path rootLocation;

    private Path cacheLocation;

    public StorageService(ImagePathConfiguration imagePathConfiguration) {
        this.rootLocation = Paths.get(imagePathConfiguration.getPath());
        this.cacheLocation = Paths.get(imagePathConfiguration.getCachePath());
    }

    @Override
    public String store(MultipartFile file) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            String fileName = Long.toString(new Date().getTime());
            Path path = this.cacheLocation.resolve(fileName + ".png");
            BufferedImage image = ImageIO.read(file.getInputStream());
            ImageIO.write(image, "png", stream);
            Files.copy(new ByteArrayInputStream(stream.toByteArray()), path);
            return fileName + ".png";
        } catch (Exception e) {
            throw new FileStoreException("store");
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
                throw new FileStoreException("load.!exists");
            }
        } catch (MalformedURLException e) {
            throw new FileStoreException("MalformedURL");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        if (Files.notExists(rootLocation)) {
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                throw new FileStoreException("init");
            }
        }
        if (Files.notExists(cacheLocation)) {
            try {
                Files.createDirectory(cacheLocation);
            } catch (IOException e) {
                throw new FileStoreException("Could not initialize cache directory!");
            }
        }
    }

    @Override
    public String moveToPersistentLocation(String fileName, Long imageId) {
        try {
            Path cacheImage = this.cacheLocation.resolve(fileName);
            if (Files.exists(cacheImage)) {
                Path targetImage = this.rootLocation.resolve(imageId + ".png");
                if (Files.exists(targetImage)) {
                    throw new FileStoreException("Target image already exists");
                }
                Files.move(cacheImage, targetImage);
                return targetImage.getFileName().toString();
            } else {
                throw new FileStoreException("Chosen image does not exist");
            }
        } catch (IOException e) {
            throw new FileStoreException("Could not move image to persistent location");
        }
    }
}
