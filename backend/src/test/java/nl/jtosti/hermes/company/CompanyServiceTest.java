package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompanyService.class)
@DisplayName("User Service")
@Tag("services")
class CompanyServiceTest {

    @Autowired
    private CompanyServiceInterface companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private LocationServiceInterface locationService;

    private User user = new User("Alex", "Jones", "Alex.jones@alex.com", "pass");

    private Company company = new Company("", "", "", "", "", "", "");

    @Test
    @DisplayName("Get all companies")
    void shouldReturnListOfCompanies_whenGetAllCompanies() {
        Company company1 = new Company("", "", "", "", "", "", "");
        List<Company> companies = Arrays.asList(company1, company);

        when(companyRepository.findAll()).thenReturn(companies);

        List<Company> found = companyService.getAllCompanies();
        assertThat(found)
                .hasSameSizeAs(companies)
                .contains(company)
                .contains(company1);
    }

    @Test
    @DisplayName("Get company by id")
    void shouldReturnCompany_whenGetCompanyById() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Company found = companyService.getCompanyById(1L);
        assertThat(found)
                .isEqualTo(company);
    }

    @Test
    @DisplayName("Get company by unknown id")
    void shouldThrowCompanyNotFoundException_whenGetCompanyByUnknownId() {
        when(companyRepository.findById(2L)).thenThrow(new CompanyNotFoundException(2L));

        try {
            companyService.getCompanyById(2L);
            fail("No error was thrown!");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(CompanyNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Save company")
    void shouldReturnSavedCompany_whenSaveCompany() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company newCompany = companyService.save(company);
        assertThat(newCompany)
                .isNotNull()
                .isEqualTo(company);
    }

    @Test
    @DisplayName("Delete company")
    void shouldDoNothing_whenDeleteCompany() {
        doNothing().when(locationService).delete(1L);
    }


}
