package codegym.service.impl;

import codegym.model.Haisan;
import codegym.model.Phanloai;
import codegym.repository.HaisanRepository;
import codegym.service.HaisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HaisanServiceImpl implements HaisanService {


@Autowired
HaisanRepository haisanRepository;


    @Override
    public Iterable<Haisan> findAll() {
        return haisanRepository.findAll();
    }

    @Override
    public Iterable<Haisan> search(String q) {
        return haisanRepository.findByNameContaining(q);
    }

    @Override
    public Haisan findOne(int id) {
        return haisanRepository.findOne(id);
    }

    @Override
    public void save(Haisan haisan) {
        haisanRepository.save(haisan);
    }

    @Override
    public void delete(Haisan haisan) {
        haisanRepository.delete(haisan);
    }

    @Override
    public Iterable<Haisan> findAllByPhanloai(Phanloai phanloai) {
        return haisanRepository.findAllByPhanloai(phanloai);
    }
}
