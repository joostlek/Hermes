package nl.jtosti.hermes.company.controller;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.company.dto.CompanyDTO;
import nl.jtosti.hermes.company.dto.ExtendedCompanyDTO;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/users/{userId}/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        Company company = convertToEntity(companyDTO);
        company.addUser(user);
        Company addedCompany = companyService.save(company);
        return convertToDTO(addedCompany);
    }

    @DeleteMapping("/companies/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
    }

    private CompanyDTO convertToDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    private ExtendedCompanyDTO convertToExtendedDTO(Company company) {
        return modelMapper.map(company, ExtendedCompanyDTO.class);
    }

    private Company convertToEntity(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }
}
