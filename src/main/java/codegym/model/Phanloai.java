package codegym.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "phanloais")
public class Phanloai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(targetEntity = Haisan.class)
    private List<Haisan> haisans;


    public Phanloai() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Haisan> getHaisans() {
        return haisans;
    }

    public void setHaisans(List<Haisan> haisans) {
        this.haisans = haisans;
    }
}
