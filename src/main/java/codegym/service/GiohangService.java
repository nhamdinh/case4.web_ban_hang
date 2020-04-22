package codegym.service;

import codegym.model.Giohang;

public interface GiohangService {

    Iterable<Giohang> findAll();

    Giohang findOne(int id);

    void save(Giohang giohang);

    void delete(Giohang giohang);




}
