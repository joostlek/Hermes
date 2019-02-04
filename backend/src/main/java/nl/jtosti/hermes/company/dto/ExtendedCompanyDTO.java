package nl.jtosti.hermes.company.dto;

import nl.jtosti.hermes.image.dto.ImageDTO;
import nl.jtosti.hermes.location.dto.LocationDTO;
import nl.jtosti.hermes.user.dto.UserDTO;

import java.util.List;

public class ExtendedCompanyDTO extends CompanyDTO {
    private List<UserDTO> users;
    private List<LocationDTO> locations;
    private List<ImageDTO> images;

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }
}
