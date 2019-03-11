package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.*;
import nl.jtosti.hermes.config.acl.AclServiceInterface;
import nl.jtosti.hermes.config.acl.MyPermission;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.exception.CompanyNotAdvertisingException;
import nl.jtosti.hermes.location.exception.LocationAlreadyAddedException;
import nl.jtosti.hermes.location.exception.LocationIsFromCompanyException;
import nl.jtosti.hermes.user.User;
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

    private final AclServiceInterface aclService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, AclServiceInterface aclService) {
        this.companyRepository = companyRepository;
        this.aclService = aclService;
    }


    @Override
    @PreAuthorize("hasRole('USER')")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public Company save(Company company) {
        Company newCompany = this.saveCompany(company);
        aclService.addPermissionsToObject(newCompany, new GrantedAuthoritySid("USER"), MyPermission.READ);
        aclService.addPermissionsToObject(newCompany, new PrincipalSid(SecurityContextHolder.getContext().getAuthentication().getName()), MyPermission.EMPLOYEE);
        return newCompany;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#company, 'EMPLOYEE')")
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    @Override
    @PreAuthorize("hasPermission(#user, 'ADMINISTRATION')")
    public List<Company> getAllCompaniesByUser(User user) {
        return Stream.concat(this.getAdvertisingCompaniesByUser(user).stream(),
                this.getPersonalCompaniesByUser(user).stream())
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasPermission(#user, 'ADMINISTRATION')")
    public List<Company> getPersonalCompaniesByUser(User user) {
        return companyRepository.findCompaniesByUserId(user.getId());
    }

    @Override
    @PreAuthorize("hasPermission(#user, 'ADMINISTRATION')")
    public List<Company> getAdvertisingCompaniesByUser(User user) {
        List<Company> companies = new ArrayList<>();
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
    @PreAuthorize("hasPermission(#newCompany, 'EMPLOYEE')")
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
    @PreAuthorize("hasPermission(#company, 'EMPLOYEE')")
    public void addUserToCompany(Company company, User user) {
        if (company.hasUser(user)) {
            throw new UserAlreadyAddedException();
        }
        company.addUser(user);
        aclService.addPermissionsToObject(company, new PrincipalSid(user.getEmail()), MyPermission.EMPLOYEE);
        this.saveCompany(company);
    }

    @Override
    @PreAuthorize("hasPermission(#company, 'EMPLOYEE')")
    public void removeUserFromCompany(Company company, User user) {
        if (!company.hasUser(user)) {
            throw new UserNotInCompanyException();
        }
        if (company.getUsers().size() <= 1) {
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
    public Company addAdvertisingLocationToCompany(Company company, Location location) {
        if (location.getCompany().equals(company)) {
            throw new LocationIsFromCompanyException();
        }
        if (company.hasAdvertisingLocation(location)) {
            throw new LocationAlreadyAddedException();
        }
        company.addAdvertisingLocation(location);
        return this.saveCompany(company);
    }

    private Company saveCompany(Company company) {
        return this.companyRepository.save(company);
    }
}
