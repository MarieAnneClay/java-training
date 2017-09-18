package model;

import java.sql.Timestamp;

public class Computer {

    private long id;
    private String name;
    private Timestamp introduced;
    private Timestamp discontinued;
    private long companyId;

    /** DEFAULT CONSTRUCTOR. */
    public Computer() {
        super();
    }

    /**
     * CONSTRUCTOR with id.
     * 
     * @param id
     *            id of the company in the database
     * @param name
     *            name of the company in the database
     * @param introduced
     *            DATETIME of the day the computer has been introduced in the
     *            company
     * @param discontinued
     *            DATETIME of the day the computer has been discontinued
     * @param companyId
     *            id of the company which possess this computer
     */
    public Computer(long id, String name, Timestamp introduced, Timestamp discontinued, long companyId) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
    }

    /**
     * CONSTRUCTOR with id.
     * 
     * @param name
     *            name of the company in the database
     * @param introduced
     *            DATETIME of the day the computer has been introduced in the
     *            company
     * @param discontinued
     *            DATETIME of the day the computer has been discontinued
     * @param companyId
     *            id of the company which possess this computer
     */
    public Computer(String name, Timestamp introduced, Timestamp discontinued, long companyId) {
        super();
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
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

    public Timestamp getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Timestamp introduced) {
        this.introduced = introduced;
    }

    public Timestamp getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Timestamp discontinued) {
        this.discontinued = discontinued;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

}
