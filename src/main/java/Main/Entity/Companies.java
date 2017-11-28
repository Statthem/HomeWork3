package Main.Entity;

import javax.persistence.*;

@Entity
@Table(name = "COMPANIES")
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "COMPANIE_NAME")
    private String companie_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanie_name() {
        return companie_name;
    }

    public void setCompanie_name(String companie_name) {
        this.companie_name = companie_name;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id=" + id +
                ", companie_name='" + companie_name + '\'' +
                '}';
    }
}
