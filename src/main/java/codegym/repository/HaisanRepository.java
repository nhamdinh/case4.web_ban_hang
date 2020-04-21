package codegym.repository;

import codegym.model.Haisan;
import codegym.model.Phanloai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HaisanRepository extends JpaRepository<Haisan, Integer> {
    List<Haisan> findByNameContaining(String q);
    Iterable<Haisan> findAllByPhanloai(Phanloai phanloai);

}
