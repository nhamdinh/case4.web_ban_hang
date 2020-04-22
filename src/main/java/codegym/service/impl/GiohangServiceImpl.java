package codegym.service.impl;

import codegym.model.Giohang;
import codegym.repository.GiohangRepository;
import codegym.service.GiohangService;
import org.springframework.beans.factory.annotation.Autowired;

public class GiohangServiceImpl implements GiohangService {
    @Autowired
    private GiohangRepository giohangRepository;
    
    @Override
    public Iterable<Giohang> findAll() {
        return giohangRepository.findAll();
    }

    @Override
    public Giohang findOne(int id) {
        return giohangRepository.findOne(id);
    }

    @Override
    public void save(Giohang giohang) {
        giohangRepository.save(giohang);
    }

    @Override
    public void delete(Giohang giohang) {
        giohangRepository.delete(giohang);
    }
}
