package persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // @Override
    // default List<Company> findAll() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // default Company getOne(Long arg0) {
    // // TODO Auto-generated method stub
    // return null;
    // }

}
