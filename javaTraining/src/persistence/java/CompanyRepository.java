package persistence.java;

import org.springframework.data.jpa.repository.JpaRepository;

import core.java.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
