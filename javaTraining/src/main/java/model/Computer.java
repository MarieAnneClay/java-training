package model;

import java.time.LocalDate;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class Computer {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @Nullable
    private LocalDate introduced;
    @Nullable
    private LocalDate discontinued;
    @Nullable
    private Long companyId;

    /** DEFAULT CONSTRUCTOR. */
    public Computer() {
        super();
    }

    /** CONSTRUCTOR with id.
     * @param id id of the company in the database
     * @param name name of the company in the database
     * @param introduced DATETIME of the day the computer has been introduced in the
     * company
     * @param discontinued DATETIME of the day the computer has been discontinued
     * @param companyId id of the company which possess this computer */
    public Computer(Long id) {
        super();
        this.id = id;
    }

    /** CONSTRUCTOR with id.
     * @param id id of the company in the database
     * @param name name of the company in the database
     * @param introduced DATETIME of the day the computer has been introduced in the
     * company
     * @param discontinued DATETIME of the day the computer has been discontinued
     * @param companyId id of the company which possess this computer */
    public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Long companyId) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
    }

    /** CONSTRUCTOR with id.
     * @param name name of the company in the database
     * @param introduced DATETIME of the day the computer has been introduced in the
     * company
     * @param discontinued DATETIME of the day the computer has been discontinued
     * @param companyId id of the company which possess this computer */
    public Computer(String name, LocalDate introduced, LocalDate discontinued, Long companyId) {
        super();
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
    }

    public static class ComputerBuilder {
        public Computer computer;

        public ComputerBuilder() {
            this.computer = new Computer();
        }

        public ComputerBuilder setId(Long id) {
            this.computer.setId(id);
            return this;
        }

        public ComputerBuilder setName(String name) {
            this.computer.setName(name);
            return this;
        }

        public ComputerBuilder setIntroduced(LocalDate introduced) {
            this.computer.setIntroduced(introduced);
            return this;
        }

        public ComputerBuilder setDiscontinued(LocalDate discontinued) {
            this.computer.setDiscontinued(discontinued);
            return this;
        }

        public ComputerBuilder setCompanyId(Long companyId) {
            this.computer.setCompanyId(companyId);
            return this;
        }

        public Computer build() {
            return this.computer;
        }
    }

    /* GETTERS & SETTERS */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}
