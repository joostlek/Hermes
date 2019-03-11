package nl.jtosti.hermes.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.company.dto.AddUserDTO;
import nl.jtosti.hermes.company.exception.*;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationService;
import nl.jtosti.hermes.location.dto.AddAdvertisingLocationDTO;
import nl.jtosti.hermes.security.screen.ScreenAuthenticationProvider;
import nl.jtosti.hermes.security.user.UserAuthenticationProvider;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@DisplayName("Company Controller")
@Tag("Controller")
class CompanyControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LocationService locationService;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private StorageServiceInterface storageService;

    @MockBean
    private UserAuthenticationProvider authenticationProvider;

    @MockBean
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    @MockBean
    private CompanyServiceInterface companyService;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com", "");

    @Test
    @DisplayName("Get all companies")
    void shouldReturnArrayOfCompanies_whenGetListOfCompanies() throws Exception {
        Company company = new Company("", "", "", "", "", "", "");
        Company company1 = new Company("", "", "", "", "", "", "");
        List<Company> companies = Arrays.asList(company, company1);

        given(companyService.getAllCompanies()).willReturn(companies);

        mvc.perform(get("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(company.getName())))
                .andExpect(jsonPath("$[1].name", is(company1.getName())));
    }

    @Test
    @DisplayName("Get single companies")
    void shouldReturnJsonObject_whenGetSingleCompany() throws Exception {
        Company company = new Company("", "kek", "", "", "", "", "");

        given(companyService.getCompanyById(1L)).willReturn(company);

        mvc.perform(get("/api/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(company.getName())));
    }

    @Test
    @DisplayName("Get single non-existent companies")
    void shouldReturnError_whenGetNonExistentCompany() throws Exception {
        when(companyService.getCompanyById(1L)).thenThrow(new CompanyNotFoundException(1L));

        mvc.perform(get("/api/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", notNullValue()));
    }

    @Test
    @DisplayName("Update company")
    void shouldReturnJsonObject_whenUpdateCompany() throws Exception {
        Company company = new Company("", "234", "", "", "", "", "");

        given(companyService.updateCompany(any(Company.class))).willReturn(company);

        mvc.perform(put("/api/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .content(objectMapper.writer().writeValueAsString(company)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(company.getName())));
    }

    @Test
    @DisplayName("Save company")
    void shouldReturnJsonObject_whenSaveCompany() throws Exception {
        Company company = new Company("", "", "", "", "", "", "");
        Company company1 = new Company("", "", "", "", "", "", "");

        when(userService.getUserById(1L)).thenReturn(user);
        given(companyService.save(any(Company.class))).willReturn(company1);

        mvc.perform(post("/api/users/1/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .content(objectMapper.writer().writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(company1.getName())));
    }

    @Test
    @DisplayName("Save company")
    void shouldReturnJsonArrayWithEmptySets_whenSaveCompanyWithoutLists() throws Exception {
        Company company = new Company("", "", "", "", "", "", "");
        company.setAdvertisingLocations(null);
        company.setLocations(null);
        company.setImages(null);
        company.setUsers(null);
        Company company1 = new Company("", "", "", "", "", "", "");

        when(userService.getUserById(1L)).thenReturn(user);
        given(companyService.save(any(Company.class))).willReturn(company1);

        mvc.perform(post("/api/users/1/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .content(objectMapper.writer().writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(company1.getName())));
    }

    @Test
    @DisplayName("Get all companies by user id")
    void shouldReturnArrayOfCompanies_whenGetListOfCompaniesByUserId() throws Exception {
        user.setId(1L);
        Company company = new Company("", "", "", "", "", "", "");
        Company company1 = new Company("", "", "", "", "", "", "");
        List<Company> companies = Arrays.asList(company, company1);

        when(userService.getUserById(1L)).thenReturn(user);
        given(companyService.getAllCompaniesByUser(user)).willReturn(companies);

        mvc.perform(get("/api/users/1/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(company.getName())))
                .andExpect(jsonPath("$[1].name", is(company1.getName())));
    }

    @Test
    @DisplayName("Get all personal companies")
    void shouldReturnArrayOfCompanies_whenGetListOfPersonalCompanies() throws Exception {
        user.setId(1L);
        Company company = new Company("", "", "", "", "", "", "");
        Company company1 = new Company("", "", "", "", "", "", "");
        List<Company> companies = Arrays.asList(company, company1);

        when(userService.getUserById(1L)).thenReturn(user);
        given(companyService.getPersonalCompaniesByUser(user)).willReturn(companies);

        mvc.perform(get("/api/users/1/companies/personal")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(company.getName())))
                .andExpect(jsonPath("$[1].name", is(company1.getName())));
    }

    @Test
    @DisplayName("Get all advertising companies")
    void shouldReturnArrayOfCompanies_whenGetListOfAdvertisingCompanies() throws Exception {
        user.setId(1L);
        Company company = new Company("", "", "", "", "", "", "");
        Company company1 = new Company("", "", "", "", "", "", "");
        List<Company> companies = Arrays.asList(company, company1);

        when(userService.getUserById(1L)).thenReturn(user);
        given(companyService.getAdvertisingCompaniesByUser(user)).willReturn(companies);

        mvc.perform(get("/api/users/1/companies/advertising")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(company.getName())))
                .andExpect(jsonPath("$[1].name", is(company1.getName())));
    }

    @Test
    @DisplayName("Delete company")
    void shouldDoNothing_whenDeleteCompany() throws Exception {
        mvc.perform(delete("/api/companies/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Add advertising location to company")
    void shouldReturnUpdatedCompany_whenAddAdvertisingLocation() throws Exception {
        Company company = new Company("", "asd", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);
        company.addAdvertisingLocation(location);

        AddAdvertisingLocationDTO advertisingDTO = new AddAdvertisingLocationDTO();
        advertisingDTO.setLocationId(1L);

        when(locationService.getLocationById(1L)).thenReturn(location);
        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(companyService.addAdvertisingLocationToCompany(any(Company.class), any(Location.class))).thenReturn(company);

        mvc.perform(post("/api/companies/1/advertising")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .content(objectMapper.writer().writeValueAsString(advertisingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(company.getName())))
                .andExpect(jsonPath("$.advertisingLocations", hasSize(1)));
    }

    @Test
    @DisplayName("Remove advertising location from company")
    void shouldReturnUpdatedCompany_whenRemoveAdvertisingLocation() throws Exception {
        Company company = new Company("", "asd", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);

        AddAdvertisingLocationDTO advertisingDTO = new AddAdvertisingLocationDTO();
        advertisingDTO.setLocationId(1L);

        when(locationService.getLocationById(1L)).thenReturn(location);
        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(companyService.removeAdvertisingLocationFromCompany(any(Company.class), any(Location.class))).thenReturn(company);

        mvc.perform(delete("/api/companies/1/advertising/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(company.getName())))
                .andExpect(jsonPath("$.advertisingLocations", hasSize(0)));

    }

    @Test
    @DisplayName("Remove advertising location from company")
    void shouldReturnError_whenDeleteAdvertisingLocationWithImages() throws Exception {
        Company company = new Company("", "asd", "", "", "", "", "");
        Location location = new Location("", "", "", "", "", "", company);

        when(locationService.getLocationById(1L)).thenReturn(location);
        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(companyService.removeAdvertisingLocationFromCompany(any(Company.class), any(Location.class))).thenThrow(new LocationHasImagesException("henk"));

        mvc.perform(delete("/api/companies/1/advertising/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", notNullValue()));

    }

    @Test
    @DisplayName("Add user to company")
    void shouldDoNothing_whenAddUserToCompany() throws Exception {
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setEmail("alex.jones@alex.com");

        mvc.perform(put("/api/companies/1/users")
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Add user to company")
    void shouldReturnError_whenAddAlreadyAddedUser() throws Exception {
        User user = new User("Alex", "Jones", "Alex.jones@akex.com", "");
        Company company = new Company("", "asd", "", "", "", "", "");
        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(userService.getUserByEmail(any(String.class))).thenReturn(user);
        doThrow(new UserAlreadyAddedException()).when(companyService).addUserToCompany(any(Company.class), any(User.class));
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setEmail("alex.jones@alex.com");

        mvc.perform(put("/api/companies/1/users")
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Remove user from company")
    void shouldDoNothing_whenRemoveUserFromCompany() throws Exception {
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setEmail("alex.jones@alex.com");

        mvc.perform(delete("/api/companies/1/users/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Remove last user from company")
    void shouldReturnError_whenDeleteLastUser() throws Exception {
        User user = new User("Alex", "Jones", "Alex.jones@akex.com", "");
        Company company = new Company("", "asd", "", "", "", "", "");
        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(userService.getUserById(1L)).thenReturn(user);
        doThrow(new LastUserException()).when(companyService).removeUserFromCompany(any(Company.class), any(User.class));

        mvc.perform(delete("/api/companies/1/users/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isConflict());
    }


    @Test
    @DisplayName("Remove not-added user from company")
    void shouldReturnError_whenRemoveNotAddedUser() throws Exception {
        User user = new User("Alex", "Jones", "Alex.jones@akex.com", "");
        Company company = new Company("", "asd", "", "", "", "", "");
        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(userService.getUserById(1L)).thenReturn(user);
        doThrow(new UserNotInCompanyException()).when(companyService).removeUserFromCompany(any(Company.class), any(User.class));

        mvc.perform(delete("/api/companies/1/users/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isConflict());
    }

}