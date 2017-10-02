package dto;

public class ComputerDTO {

    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String companyId;

    public ComputerDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ComputerDTO(String id, String name, String introduced, String discontinued, String companyId) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
    }

    public ComputerDTO(String name, String introduced, String discontinued, String companyId) {
        super();
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
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

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public static class ComputerBuilder {
        public ComputerDTO computerDTO;

        public ComputerBuilder() {
            this.computerDTO = new ComputerDTO();
        }

        public ComputerBuilder setId(String id) {
            this.computerDTO.setId(id);
            return this;
        }

        public ComputerBuilder setName(String name) {
            this.computerDTO.setName(name);
            return this;
        }

        public ComputerBuilder setIntroduced(String introduced) {
            this.computerDTO.setIntroduced(introduced);
            return this;
        }

        public ComputerBuilder setDiscontinued(String discontinued) {
            this.computerDTO.setDiscontinued(discontinued);
            return this;
        }

        public ComputerBuilder setCompanyId(String companyId) {
            this.computerDTO.setCompanyId(companyId);
            return this;
        }

        public ComputerDTO build() {
            return this.computerDTO;
        }
    }
}
