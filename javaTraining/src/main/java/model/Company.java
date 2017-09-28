package model;

public class Company {

    private long id;
    private String name;

    /** DEFAULT CONSTRUCTOR. */
    public Company() {
        super();
    }

    /** CONSTRUCTOR with id.
     * @param id id of the company in the database */
    public Company(String name) {
        super();
        this.name = name;
    }

    /** CONSTRUCTOR with id and name.
     * @param id id of the company in the database */
    public Company(long id) {
        super();
        this.id = id;
    }

    /** CONSTRUCTOR with id and name.
     * @param id id of the company in the database
     * @param name name of the company in the database */
    public Company(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static class CompanyBuilder {
        Company company;

        public CompanyBuilder() {
            this.company = new Company();
        }

        public CompanyBuilder setId(long id) {
            this.company.setId(id);
            return this;
        }

        public CompanyBuilder setName(String name) {
            this.company.setName(name);
            return this;
        }

        public Company build() {
            return this.company;
        }
    }

    /* GETTERS & SETTERS */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
