package model;

public class Company {

    private long id;
    private String name;

    /** DEFAULT CONSTRUCTOR. */
    public Company() {
        super();
    }

    /**
     * CONSTRUCTOR with id.
     * 
     * @param id
     *            id of the company in the database
     */
    public Company(long id) {
        super();
        this.id = id;
    }

    /**
     * CONSTRUCTOR with id and name.
     * 
     * @param id
     *            id of the company in the database
     * @param name
     *            name of the company in the database
     */
    public Company(long id, String name) {
        super();
        this.id = id;
        this.name = name;
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
