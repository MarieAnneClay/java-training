package DTO;

public class CompanyDTO {
    private String id;
    private String name;

    public CompanyDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CompanyDTO(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public CompanyDTO(String name) {
        super();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class CompanyBuilder {
        CompanyDTO companyDTO;

        public CompanyBuilder() {
            this.companyDTO = new CompanyDTO();
        }

        public CompanyBuilder setId(String id) {
            this.companyDTO.setId(id);
            return this;
        }

        public CompanyBuilder setName(String name) {
            this.companyDTO.setName(name);
            return this;
        }

        public CompanyDTO build() {
            return this.companyDTO;
        }
    }

}
