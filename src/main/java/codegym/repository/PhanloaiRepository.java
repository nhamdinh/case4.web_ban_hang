package codegym.repository;

import codegym.model.Phanloai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhanloaiRepository extends JpaRepository<Phanloai, Integer> {

    List<Phanloai> findByNameContaining(String q);


}
