package nl.jtosti.hermes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("hermes.images")
public class ImagePathConfiguration {
    private String path = "upload-dir";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
