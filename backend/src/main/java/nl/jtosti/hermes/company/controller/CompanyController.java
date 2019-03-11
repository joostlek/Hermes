package nl.jtosti.hermes.company.controller;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.company.dto.AddUserDTO;
import nl.jtosti.hermes.company.dto.CompanyDTO;
import nl.jtosti.hermes.company.dto.ExtendedCompanyDTO;
import nl.jtosti.hermes.config.V1ApiController;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationService;
import nl.jtosti.hermes.location.dto.AddAdvertisingLocationDTO;
import nl.jtosti.hermes.location.exception.LocationNotSelectedException;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@V1ApiController
public class CompanyController {

    private final CompanyServiceInterface companyService;
    private final ModelMapper modelMapper;
    private final UserServiceInterface userService;
    private final LocationService locationService;


    @Autowired
    public CompanyController(CompanyServiceInterface companyService, ModelMapper modelMapper, UserServiceInterface userService, LocationService locationService) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.locationService = locationService;
    }


    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDTO> getAllCompanies() {
        return companyService.getAllCompanies().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/companies/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public ExtendedCompanyDTO getCompanyById(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return convertToExtendedDTO(company);
    }

    @PutMapping("/companies/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO updateCompany(@RequestBody ExtendedCompanyDTO companyDTO, @PathVariable Long companyId) {
        Company company = convertToEntity(companyDTO);
        company.setId(companyId);
        Company updatedCompany = companyService.updateCompany(company);
        return convertToExtendedDTO(updatedCompany);
    }

    @PostMapping("/users/{userId}/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public ExtendedCompanyDTO createCompany(@RequestBody ExtendedCompanyDTO companyDTO, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        Company company = convertToEntity(companyDTO);
        company.addUser(user);
        Company addedCompany = companyService.save(company);
        return convertToExtendedDTO(addedCompany);
    }

    @GetMapping("/users/{userId}/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedCompanyDTO> getAllCompaniesByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return companyService.getAllCompaniesByUser(user)
                .stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/companies/personal")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedCompanyDTO> getPersonalCompaniesByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return companyService.getPersonalCompaniesByUser(user)
                .stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/companies/advertising")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedCompanyDTO> getAdvertisingCompaniesByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return companyService.getAdvertisingCompaniesByUser(user)
                .stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/companies/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        companyService.deleteCompany(company);
    }

    @DeleteMapping("/companies/{companyId}/advertising/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public ExtendedCompanyDTO removeAdvertisingCompanyFromLocation(@PathVariable Long locationId, @PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        Location location = locationService.getLocationById(locationId);
        return convertToExtendedDTO(companyService.removeAdvertisingLocationFromCompany(company, location));
    }

    @PostMapping("/companies/{companyId}/advertising")
    @ResponseStatus(HttpStatus.OK)
    public ExtendedCompanyDTO addAdvertisingLocationToCompany(@RequestBody AddAdvertisingLocationDTO locationDTO, @PathVariable Long companyId) {
        Long locationId = locationDTO.getLocationId();
        if (locationId == 0) {
            throw new LocationNotSelectedException();
        }
        Location location = locationService.getLocationById(locationId);
        Company company = companyService.getCompanyById(companyId);
        return convertToExtendedDTO(companyService.addAdvertisingLocationToCompany(company, location));
    }

    @PutMapping("/companies/{companyId}/users")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToCompany(@RequestBody AddUserDTO userDTO, @PathVariable Long companyId) {
        User user = userService.getUserByEmail(userDTO.getEmail());
        Company company = companyService.getCompanyById(companyId);
        companyService.addUserToCompany(company, user);
    }

    @DeleteMapping("/companies/{companyId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromCompany(@PathVariable Long companyId, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        Company company = companyService.getCompanyById(companyId);
        companyService.removeUserFromCompany(company, user);
    }

    private CompanyDTO convertToDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    private ExtendedCompanyDTO convertToExtendedDTO(Company company) {
        return modelMapper.map(company, ExtendedCompanyDTO.class);
    }

    private Company convertToEntity(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        if (company.getUsers() == null) {
            company.setUsers(new HashSet<>());
        }
        if (company.getLocations() == null) {
            company.setLocations(new HashSet<>());
        }
        if (company.getImages() == null) {
            company.setImages(new HashSet<>());
        }
        if (company.getAdvertisingLocations() == null) {
            company.setAdvertisingLocations(new HashSet<>());
        }
        return company;
    }
}
