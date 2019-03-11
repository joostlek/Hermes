package nl.jtosti.hermes.company;

import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.user.User;

import java.util.List;

public interface CompanyServiceInterface {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company save(Company company);

    void deleteCompany(Company company);

    List<Company> getAllCompaniesByUser(User user);

    List<Company> getPersonalCompaniesByUser(User user);

    List<Company> getAdvertisingCompaniesByUser(User user);

    Company updateCompany(Company company);

    void addUserToCompany(Company company, User user);

    void removeUserFromCompany(Company company, User user);

    Company removeAdvertisingLocationFromCompany(Company company, Location location);

    Company addAdvertisingLocationToCompany(Company company, Location location);
}
