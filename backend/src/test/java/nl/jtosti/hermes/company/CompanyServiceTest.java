package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.company.exception.LastUserException;
import nl.jtosti.hermes.company.exception.UserAlreadyAddedException;
import nl.jtosti.hermes.location.Location;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompanyService.class)
@DisplayName("Company Service")
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

        locationService.delete(1L);
    }

    @Test
    @DisplayName("Get all companies by user id")
    void shouldReturnCombinedStream_whenGetAllCompaniesByUserId() {
        Company anotherCompany = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", anotherCompany);
        Location location1 = new Location("", "", "", "", "", "", anotherCompany);
        company.addAdvertisingLocation(location);
        company.addAdvertisingLocation(location1);
        user.addCompany(company);
        when(companyRepository.findCompaniesByUserId(1L)).thenReturn(Collections.singletonList(company));


        when(userService.getUserById(1L)).thenReturn(user);

        assertThat(companyService.getAllCompaniesByUserId(1L))
                .hasSize(2)
                .contains(anotherCompany)
                .contains(company);

    }

    @Test
    @DisplayName("Get all personal companies by user id")
    void shouldReturnAllPersonalCompanies_whenGetAllPersonalCompaniesByUserId() {
        when(companyRepository.findCompaniesByUserId(1L)).thenReturn(Collections.singletonList(company));

        assertThat(companyService.getPersonalCompaniesByUserID(1L))
                .hasSize(1)
                .contains(company);
    }

    @Test
    @DisplayName("Get all advertising companies by user id")
    void shouldReturnAllAdvertisingCompanies_whenGetAllAdvertisingCompaniesByUserId() {
        Company anotherCompany = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", anotherCompany);
        Location location1 = new Location("", "", "", "", "", "", anotherCompany);
        company.addAdvertisingLocation(location);
        company.addAdvertisingLocation(location1);
        user.addCompany(company);

        when(userService.getUserById(1L)).thenReturn(user);

        assertThat(companyService.getAdvertisingCompaniesByUserId(1L))
                .hasSize(1)
                .contains(anotherCompany);
    }

    @Test
    @DisplayName("Update company")
    void shouldUpdateCompany_whenUpdateCompany() {
        Company company = new Company("", "", "", "", "", "", "");

        Company company1 = new Company("123", "asd", "asd", "qwe", "asd", "asd", "asd");
        company1.setId(1L);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company1);

        assertThat(companyService.updateCompany(company1))
                .isEqualTo(company1);
    }

    @Test
    @DisplayName("Add user to company")
    void shouldAddUserToCompany_whenAddUserToCompany() {
        when(userService.getUserByEmail("alex.jones@alex.com")).thenReturn(user);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        companyService.addUserToCompany(1L, "alex.jones@alex.com");
    }

    @Test
    @DisplayName("Remove user from company")
    void shouldRemoveUserFromCompany_whenRemoveUser() {
        company.addUser(user);
        company.addUser(new User("", "", "", ""));
        when(userService.getUserById(1L)).thenReturn(user);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        companyService.removeUserFromCompany(1L, 1L);
    }

    @Test
    @DisplayName("Remove last user from company")
    void shouldThrowLastUserException_whenRemoveLastUser() {
        company.addUser(user);
        when(userService.getUserById(1L)).thenReturn(user);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        try {
            companyService.removeUserFromCompany(1L, 1L);
            fail("User was added, even though it was already added!");
        } catch (RuntimeException e) {
            assertThat(e)
                    .isInstanceOf(LastUserException.class);
        }
    }

    @Test
    @DisplayName("Add already added user to company")
    void shouldThrowUserAlreadyAddedException_whenAddAlreadyAddedUserToCompany() {
        company.addUser(user);
        when(userService.getUserByEmail("alex.jones@alex.com")).thenReturn(user);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        try {
            companyService.addUserToCompany(1L, "alex.jones@alex.com");
            fail("User was added, even though it was already added!");
        } catch (RuntimeException e) {
            assertThat(e)
                    .isInstanceOf(UserAlreadyAddedException.class);
        }
    }

    @Test
    @DisplayName("Add already added user to company")
    void shouldThrowUserNotInCompanyException_whenRemoveUserNotInCompany() {
        company.addUser(user);
        when(userService.getUserByEmail("alex.jones@alex.com")).thenReturn(user);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        try {
            companyService.addUserToCompany(1L, "alex.jones@alex.com");
            fail("User was added, even though it was already added!");
        } catch (RuntimeException e) {
            assertThat(e)
                    .isInstanceOf(UserAlreadyAddedException.class);
        }
    }


}
