package main.model;

import java.time.LocalDate;

public class Computer {

  private long id;
  private String name;
  private LocalDate introduced;
  private LocalDate discontinued;
  private long companyId;

  /* CONSTRUCTEURS */
  public Computer() {
    super();
  }

  public Computer(long id, String name, LocalDate introduced,
      LocalDate discontinued, long companyId) {
    super();
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.companyId = companyId;
  }

  public Computer(String name, LocalDate introduced, LocalDate discontinued,
      long companyId) {
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

  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

}
