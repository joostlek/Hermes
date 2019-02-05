package nl.jtosti.hermes.image.dto;

public class FileDTO {
    private String url;

    public FileDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
