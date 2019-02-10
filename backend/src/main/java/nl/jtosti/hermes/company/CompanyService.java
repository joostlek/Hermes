package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.company.exception.LastUserException;
import nl.jtosti.hermes.company.exception.UserAlreadyAddedException;
import nl.jtosti.hermes.company.exception.UserNotInCompanyException;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
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
        return companyRepository.findCompaniesByUserId(userId);
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
}
