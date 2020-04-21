package codegym.model;


import javax.persistence.*;

@Entity
@Table(name = "haisans")
public class Haisan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    private String date;
    private String noiban;
    private String cachnau;

    private int soluong;
    private int giamua;
    private int giaban;

    @ManyToOne
    @JoinColumn(name="phanloai_id")
    private Phanloai phanloai;

    public Haisan() {
    }

    public Phanloai getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(Phanloai phanloai) {
        this.phanloai = phanloai;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoiban() {
        return noiban;
    }

    public void setNoiban(String noiban) {
        this.noiban = noiban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiamua() {
        return giamua;
    }

    public void setGiamua(int giamua) {
        this.giamua = giamua;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public String getCachnau() {
        return cachnau;
    }

    public void setCachnau(String cachnau) {
        this.cachnau = cachnau;
    }
}
