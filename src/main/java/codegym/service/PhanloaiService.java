package codegym.service;

import codegym.model.Phanloai;

public interface PhanloaiService {

	Iterable<Phanloai> findAll();

    Iterable<Phanloai> search(String q);

    Phanloai findOne(int id);

    void save(Phanloai phanloai);

    void delete(Phanloai phanloai);
}
