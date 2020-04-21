package codegym.service;

import codegym.model.Haisan;
import codegym.model.Phanloai;

public interface HaisanService {

    Iterable<Haisan> findAll();

    Iterable<Haisan> search(String q);

    Haisan findOne(int id);

    void save(Haisan haisan);

    void delete(Haisan haisan);

    Iterable<Haisan> findAllByPhanloai(Phanloai phanloai);

}
