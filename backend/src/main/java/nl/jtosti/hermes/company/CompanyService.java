package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.*;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.location.exception.CompanyNotAdvertisingException;
import nl.jtosti.hermes.location.exception.LocationAlreadyAddedException;
import nl.jtosti.hermes.location.exception.LocationIsFromCompanyException;
import nl.jtosti.hermes.location.exception.LocationNotSelectedException;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;
    private final UserServiceInterface userService;
    private final LocationServiceInterface locationService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserServiceInterface userService, LocationServiceInterface locationService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.locationService = locationService;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompaniesByUserId(Long userId) {
        return Stream.concat(this.getAdvertisingCompaniesByUserId(userId).stream(),
                this.getPersonalCompaniesByUserID(userId).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Company> getPersonalCompaniesByUserID(Long userId) {
        return companyRepository.findCompaniesByUserId(userId);
    }

    @Override
    public List<Company> getAdvertisingCompaniesByUserId(Long userId) {
        return companyRepository.findAdvertisingCompaniesByUserId(userId);
    }

    @Override
    public Company updateCompany(Company newCompany) {
        Company company = this.getCompanyById(newCompany.getId());
        company.setName(newCompany.getName());
        company.setStreet(newCompany.getStreet());
        company.setHouseNumber(newCompany.getHouseNumber());
        company.setZipCode(newCompany.getZipCode());
        company.setCountry(newCompany.getCountry());
        company.setCity(newCompany.getCity());
        return save(company);
    }

    @Override
    public void addUserToCompany(Long companyId, String email) {
        User user = userService.getUserByEmail(email);
        Company company = this.getCompanyById(companyId);
        if (company.hasUser(user)) {
            throw new UserAlreadyAddedException();
        }
        company.addUser(user);
        companyRepository.save(company);
    }

    @Override
    public void removeUserFromCompany(Long userId, Long companyId) {
        User user = userService.getUserById(userId);
        Company company = this.getCompanyById(companyId);
        if (company.getUsers().size() == 1) {
            throw new LastUserException();
        }
        if (!company.hasUser(user)) {
            throw new UserNotInCompanyException();
        }
        company.getUsers().remove(user);
        companyRepository.save(company);
    }

    @Override
    public Company removeAdvertisingLocationFromCompany(Company company, Location location) {
        if (!location.hasAdvertisingCompany(company)) {
            throw new CompanyNotAdvertisingException(company.getName(), location.getName());
        }
        if (!company.getImagesByLocation(location).isEmpty()) {
            throw new LocationHasImagesException(location.getName());
        }
        company.getAdvertisingLocations().remove(location);
        return this.save(company);
    }

    @Override
    public Company addAdvertisingLocationToCompany(Company company, Long locationId) {
        if (locationId == 0) {
            throw new LocationNotSelectedException();
        }
        Location location = locationService.getLocationById(locationId);
        if (location.getCompany().equals(company)) {
            throw new LocationIsFromCompanyException();
        }
        if (location.hasAdvertisingCompany(company)) {
            throw new LocationAlreadyAddedException();
        }
        company.addAdvertisingLocation(location);
        return this.save(company);
    }
}
