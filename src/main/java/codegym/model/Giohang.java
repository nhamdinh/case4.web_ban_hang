package codegym.model;


import javax.persistence.*;

@Entity
@Table(name = "giohang")
public class Giohang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;


    private int soluong;
    private int giaban;


    public Giohang() {
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



    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }


}
