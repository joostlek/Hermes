package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.*;
import nl.jtosti.hermes.config.acl.AclServiceInterface;
import nl.jtosti.hermes.config.acl.MyPermission;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.location.exception.CompanyNotAdvertisingException;
import nl.jtosti.hermes.location.exception.LocationAlreadyAddedException;
import nl.jtosti.hermes.location.exception.LocationIsFromCompanyException;
import nl.jtosti.hermes.location.exception.LocationNotSelectedException;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;
    private final UserServiceInterface userService;
    private final LocationServiceInterface locationService;

    private final AclServiceInterface aclService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserServiceInterface userService, LocationServiceInterface locationService, AclServiceInterface aclService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.locationService = locationService;
        this.aclService = aclService;
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
        Company newCompany = this.saveCompany(company);
        aclService.addPermissionsToObject(newCompany, new GrantedAuthoritySid("USER"), MyPermission.READ);
        aclService.addPermissionsToObject(newCompany, new PrincipalSid(SecurityContextHolder.getContext().getAuthentication().getName()), MyPermission.EMPLOYEE);
        return newCompany;
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
        List<Company> companies = new ArrayList<>();
        User user = userService.getUserById(userId);
        for (Company company : user.getCompanies()) {
            for (Location location : company.getAdvertisingLocations()) {
                if (!companies.contains(location.getCompany())) {
                    companies.add(location.getCompany());
                }
            }
        }
        return companies;
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
        return saveCompany(company);
    }

    @Override
    public void addUserToCompany(Long companyId, String email) {
        User user = userService.getUserByEmail(email);
        Company company = this.getCompanyById(companyId);
        if (company.hasUser(user)) {
            throw new UserAlreadyAddedException();
        }
        company.addUser(user);
        aclService.addPermissionsToObject(company, new PrincipalSid(email), MyPermission.EMPLOYEE);
        this.saveCompany(company);
    }

    @Override
    public void removeUserFromCompany(Long userId, Long companyId) {
        User user = userService.getUserById(userId);
        Company company = this.getCompanyById(companyId);
        if (!company.hasUser(user)) {
            throw new UserNotInCompanyException();
        }
        if (company.getUsers().size() == 1) {
            throw new LastUserException();
        }
        company.getUsers().remove(user);
        aclService.removePermissionFromObject(company, new PrincipalSid(user.getEmail()), MyPermission.EMPLOYEE);
        this.saveCompany(company);
    }

    @Override
    @PreAuthorize("hasPermission(#company, 'EMPLOYEE')")
    public Company removeAdvertisingLocationFromCompany(Company company, Location location) {
        if (!location.hasAdvertisingCompany(company)) {
            throw new CompanyNotAdvertisingException(company.getName(), location.getName());
        }
        if (!company.getImagesByLocation(location).isEmpty()) {
            throw new LocationHasImagesException(location.getName());
        }
        company.getAdvertisingLocations().remove(location);
        return this.saveCompany(company);
    }

    @Override
    @PreAuthorize("hasPermission(#company, 'EMPLOYEE')")
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
        return this.saveCompany(company);
    }

    private Company saveCompany(Company company) {
        return this.companyRepository.save(company);
    }
}
