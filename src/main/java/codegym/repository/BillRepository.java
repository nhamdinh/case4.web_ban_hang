package codegym.repository;

import codegym.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findByUserNameContaining(String q);


}
