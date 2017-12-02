package Main.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COSTUMERS")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "COSTUMER_NAME")
    private String costumer_name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_projects", joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "projects_id")})
    private Set<Projects> projects ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCostumer_name() {
        return costumer_name;
    }

    public void setCostumer_name(String costumer_name) {
        this.costumer_name = costumer_name;
    }

    public Customers(String costumer_name) {
        this.costumer_name = costumer_name;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", costumer_name='" + costumer_name + '\'' +
                '}';
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }
}
