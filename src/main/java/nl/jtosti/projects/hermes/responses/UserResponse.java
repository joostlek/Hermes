package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
    private String role;
    private List<LocationResponse> locations;
    private List<ImageResponse> images;
    private List<PromotionResponse> promotions;

    public UserResponse(User user, boolean simple) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.street = user.getStreet();
        this.houseNumber = user.getHouseNumber();
        this.zipCode = user.getZipCode();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.role = user.getRole();
        this.locations = new ArrayList<>();
        this.images = new ArrayList<>();
        this.promotions = new ArrayList<>();
        if (user.getLocations() != null) {
            for (Location location: user.getLocations()) {
                this.addLocation(location.toResponse(true));
            }
        }
        if (user.getImages() != null) {
            for (Image image: user.getImages()) {
                this.addImage(image.toResponse(true));
            }
        }
        if (user.getPromotions() != null) {
            for (Promotion promotion: user.getPromotions()) {
                this.addPromotion(promotion.toResponse(true));
            }
        }
    }

    public UserResponse() {
    }

    public void addLocation(LocationResponse locationResponse) {
        this.locations.add(locationResponse);
    }

    public void addImage(ImageResponse imageResponse) {
        this.images.add(imageResponse);
    }

    public void addPromotion(PromotionResponse promotionResponse) {
        this.promotions.add(promotionResponse);
    }
}
