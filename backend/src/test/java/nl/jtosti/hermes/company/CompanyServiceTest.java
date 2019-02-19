package nl.jtosti.hermes.company;

import nl.jtosti.hermes.company.exception.*;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.location.exception.CompanyNotAdvertisingException;
import nl.jtosti.hermes.location.exception.LocationAlreadyAddedException;
import nl.jtosti.hermes.location.exception.LocationIsFromCompanyException;
import nl.jtosti.hermes.location.exception.LocationNotSelectedException;
import nl.jtosti.hermes.screen.Screen;
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
    @DisplayName("Remove user not added to company")
    void shouldThrowUserNotInCompanyException_whenRemoveUserNotInCompany() {
        when(userService.getUserById(1L)).thenReturn(user);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        try {
            companyService.removeUserFromCompany(1L, 1L);
            fail("User was added, even though it was already added!");
        } catch (RuntimeException e) {
            assertThat(e)
                    .isInstanceOf(UserNotInCompanyException.class);
        }
    }

    @Test
    @DisplayName("Add advertising location to company")
    void shouldAddAdvertisingLocationToCompany() {
        Company company1 = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);
        when(locationService.getLocationById(1L)).thenReturn(location);
        when(companyRepository.save(any(Company.class))).thenReturn(company1);

        assertThat(companyService.addAdvertisingLocationToCompany(company1, 1L))
                .isEqualTo(company1);
    }

    @Test
    @DisplayName("Add advertising location to same company")
    void shouldThrowLocationIsFromCompanyException_whenAddLocationToSameCompany() {
        Location location = new Location("", "", "", "", "", "", company);
        when(locationService.getLocationById(1L)).thenReturn(location);

        try {
            companyService.addAdvertisingLocationToCompany(company, 1L);
            fail("Can add location to the same location");
        } catch (RuntimeException ex) {
            assertThat(ex)
                    .isInstanceOf(LocationIsFromCompanyException.class);
        }
    }

    @Test
    @DisplayName("Add advertising location to company twice")
    void shouldThrowLocationAlreadyAddedException_whenAddLocationTwice() {
        Company company1 = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);
        when(locationService.getLocationById(1L)).thenReturn(location);

        location.addAdvertisingCompanies(company1);
        try {
            companyService.addAdvertisingLocationToCompany(company1, 1L);
            fail("Can add location to the same location");
        } catch (RuntimeException ex) {
            assertThat(ex)
                    .isInstanceOf(LocationAlreadyAddedException.class);
        }
    }

    @Test
    @DisplayName("Add advertising location to company with id 0")
    void shouldThrowLocationNotSelectedException_whenAddLocationZeroToCompany() {
        try {
            companyService.addAdvertisingLocationToCompany(company, 0L);
            fail("Can add location with id 0");
        } catch (RuntimeException ex) {
            assertThat(ex)
                    .isInstanceOf(LocationNotSelectedException.class);
        }
    }

    @Test
    @DisplayName("Remove advertising location from company")
    void shouldReturnUpdatedCompany_whenRemoveAdvertisingLocationFromCompany() {
        Company company1 = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);
        location.addAdvertisingCompanies(company1);

        when(companyRepository.save(any(Company.class))).thenReturn(company1);

        assertThat(companyService.removeAdvertisingLocationFromCompany(company1, location))
                .isEqualTo(company1);
    }

    @Test
    @DisplayName("Remove advertising location from company")
    void shouldThrowLocationHasImagesException_whenRemoveLocationWithImages() {
        Company company1 = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);
        Screen screen = new Screen("", 1920, 1080, location);
        location.addScreen(screen);
        Image image = new Image("", "", screen, user);
        company1.addImage(image);
        location.addAdvertisingCompanies(company1);

        when(companyRepository.save(any(Company.class))).thenReturn(company1);

        try {
            companyService.removeAdvertisingLocationFromCompany(company1, location);
            fail("Can remove location which isn't even present");
        } catch (RuntimeException ex) {
            assertThat(ex)
                    .isInstanceOf(LocationHasImagesException.class);
        }
    }

    @Test
    @DisplayName("Remove advertising location from company")
    void shouldThrowLocationNotAdvertisingException_whenRemoveLocationThatIsNotAdvertising() {
        Company company1 = new Company("", "", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);

        when(companyRepository.save(any(Company.class))).thenReturn(company1);
        try {
            companyService.removeAdvertisingLocationFromCompany(company1, location);
            fail("Can remove location which isn't even present");
        } catch (RuntimeException ex) {
            assertThat(ex)
                    .isInstanceOf(CompanyNotAdvertisingException.class);
        }
    }
}
