package nl.jtosti.hermes.company.dto;

import nl.jtosti.hermes.image.dto.ImageDTO;
import nl.jtosti.hermes.location.dto.LocationDTO;
import nl.jtosti.hermes.user.dto.UserDTO;

import java.util.List;
import java.util.Set;

public class ExtendedCompanyDTO extends CompanyDTO {
    private String phoneNumber;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
    private Set<LocationDTO> advertisingLocations;
    private List<UserDTO> users;
    private List<LocationDTO> locations;
    private List<ImageDTO> images;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<LocationDTO> getAdvertisingLocations() {
        return advertisingLocations;
    }

    public void setAdvertisingLocations(Set<LocationDTO> advertisingLocations) {
        this.advertisingLocations = advertisingLocations;
    }

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
