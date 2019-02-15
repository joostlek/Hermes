package nl.jtosti.hermes.company.controller;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.company.dto.AddUserDTO;
import nl.jtosti.hermes.company.dto.CompanyDTO;
import nl.jtosti.hermes.company.dto.ExtendedCompanyDTO;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    private final CompanyServiceInterface companyService;
    private final ModelMapper modelMapper;
    private final UserServiceInterface userService;

    @Autowired
    public CompanyController(CompanyServiceInterface companyService, ModelMapper modelMapper, UserServiceInterface userService) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
        this.userService = userService;
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
    @Secured({"USER", "ADMIN"})
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
        return companyService.getAllCompaniesByUserId(userId)
                .stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/companies/personal")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedCompanyDTO> getPersonalCompaniesByUserId(@PathVariable Long userId) {
        return companyService.getPersonalCompaniesByUserID(userId)
                .stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/companies/advertising")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedCompanyDTO> getAdvertisingCompaniesByUserId(@PathVariable Long userId) {
        return companyService.getAdvertisingCompaniesByUserId(userId)
                .stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/companies/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
    }


    @PutMapping("/companies/{companyId}/users")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToCompany(@RequestBody AddUserDTO userDTO, @PathVariable Long companyId) {
        companyService.addUserToCompany(companyId, userDTO.getEmail());
    }

    @DeleteMapping("/companies/{companyId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToCompany(@PathVariable Long companyId, @PathVariable Long userId) {
        companyService.removeUserFromCompany(userId, companyId);
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
