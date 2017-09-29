package DTO;

import java.util.ArrayList;

import model.Company;

public class CompanyMapper {

    public static Company convertDTOToCompany(CompanyDTO companyDTO) {
        return new Company.CompanyBuilder()
                .setId(companyDTO.getId() == "" ? 0 : Long.parseLong(companyDTO.getId()))
                .setName(companyDTO.getName())
                .build();
    }
    
    public static CompanyDTO convertCompanyToDTO(Company company) {
        return new CompanyDTO.CompanyBuilder()
                .setId(Long.toString(company.getId()))
                .setName(company.getName())
                .build();    
    }
    
    public static ArrayList<CompanyDTO> convertCompaniesToDTOS(ArrayList<Company> companies){
        ArrayList<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        for (Company company : companies) {
            companiesDTO.add(CompanyMapper.convertCompanyToDTO(company));
        }
        return companiesDTO;
    }

}
