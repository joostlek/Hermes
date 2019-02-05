package nl.jtosti.hermes.user.dto;

import nl.jtosti.hermes.company.dto.CompanyDTO;

import java.util.List;

public class ExtendedUserDTO extends UserDTO {
    private List<CompanyDTO> companies;

    public List<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDTO> companies) {
        this.companies = companies;
    }
}
