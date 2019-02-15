package nl.jtosti.hermes.location.dto;

import nl.jtosti.hermes.company.dto.CompanyDTO;

import java.util.Set;

public class ExtendedLocationDTO extends LocationDTO {
    private CompanyDTO company;
    private Set<CompanyDTO> advertisingCompanies;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public Set<CompanyDTO> getAdvertisingCompanies() {
        return advertisingCompanies;
    }

    public void setAdvertisingCompanies(Set<CompanyDTO> advertisingCompanies) {
        this.advertisingCompanies = advertisingCompanies;
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
}
