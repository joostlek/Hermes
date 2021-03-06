package nl.jtosti.hermes.company;

import nl.jtosti.hermes.location.Location;

import java.util.List;

public interface CompanyServiceInterface {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company save(Company company);

    void deleteCompany(Long id);

    List<Company> getAllCompaniesByUserId(Long userId);

    List<Company> getPersonalCompaniesByUserID(Long userId);

    List<Company> getAdvertisingCompaniesByUserId(Long userId);

    Company updateCompany(Company company);

    void addUserToCompany(Long companyId, String email);

    void removeUserFromCompany(Long userId, Long companyId);

    Company removeAdvertisingLocationFromCompany(Company company, Location location);

    Company addAdvertisingLocationToCompany(Company company, Long locationId);
}
