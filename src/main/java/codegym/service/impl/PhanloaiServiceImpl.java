package codegym.service.impl;

import codegym.model.Phanloai;
import codegym.repository.PhanloaiRepository;
import codegym.service.PhanloaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhanloaiServiceImpl implements PhanloaiService {


	@Autowired
    private PhanloaiRepository phanloaiRepository;


    @Override
    public Iterable<Phanloai> findAll() {
        return phanloaiRepository.findAll();
    }

    @Override
    public Iterable<Phanloai> search(String q) {
        return phanloaiRepository.findByNameContaining(q);
    }

    @Override
    public Phanloai findOne(int id) {
        return phanloaiRepository.findOne(id);
    }

    @Override
    public void save(Phanloai phanloai) {
        phanloaiRepository.save(phanloai);
    }

    @Override
    public void delete(Phanloai phanloai) {
        phanloaiRepository.delete(phanloai);
    }
}
