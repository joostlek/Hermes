package nl.jtosti.hermes.user.dto;

import nl.jtosti.hermes.company.dto.CompanyDTO;

import java.util.List;

public class ExtendedUserDTO extends UserDTO {
    private List<CompanyDTO> companies;
    private List<String> roles;

    public List<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDTO> companies) {
        this.companies = companies;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
