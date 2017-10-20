package persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    long countByNameContainingOrCompanyNameContaining(String name, String companyName);

    Page<Computer> findAllComputersByNameContainingOrCompanyNameContaining(Pageable page, String name, String companyName); // TODO sort+limit

    // @Override
    // default Optional<Computer> findById(Long arg0) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    //
    // @Override
    // default void deleteById(Long arg0) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // default <S extends Computer> S saveAndFlush(S arg0) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //

}
