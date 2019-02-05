package nl.jtosti.hermes.company;

import java.util.List;

public interface CompanyServiceInterface {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company save(Company company);

    void deleteCompany(Long id);
}