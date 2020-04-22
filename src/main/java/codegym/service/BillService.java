package codegym.service;

import codegym.model.Bill;

public interface BillService {

    Iterable<Bill> findAll();

    Iterable<Bill> search(String q);

    Bill findOne(int id);

    void save(Bill bill);

    void delete(Bill bill);
}
