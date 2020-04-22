package codegym.service.impl;

import codegym.model.Bill;
import codegym.repository.BillRepository;
import codegym.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {


    @Autowired
    private BillRepository billRepository;


    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Iterable<Bill> search(String q) {
        return billRepository.findByUserNameContaining(q);
    }

    @Override
    public Bill findOne(int id) {
        return billRepository.findOne(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {
        billRepository.delete(bill);
    }
}
