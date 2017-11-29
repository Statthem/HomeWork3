package Main.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COMPANIES")
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    public Companies(String companie_name) {
        this.companie_name = companie_name;
    }

    @Column(name = "COMPANIE_NAME")

    private String companie_name;

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "companies_projects", joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "companie_id")})
    private Set<Projects> projects;

    public Companies() {
    }

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
