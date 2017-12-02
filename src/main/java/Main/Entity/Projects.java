package Main.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PROJECTS")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "PROJECT_NAME")
    private String project_name;

    @Column(name = "PROJECT_DESCRIPTION")
    private String project_description;

    public Projects(String project_name, String project_description) {
        this.project_name = project_name;
        this.project_description = project_description;
    }
    public Projects() {

    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "developers_projects", joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Developers> developers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_projects", joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "projects_id")})
    private Set<Customers> customers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "companies_projects", joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "companie_id")})
    private Set<Companies> companies;

    public Set<Developers> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developers> developers) {
        this.developers = developers;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }

    public Set<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Companies> companies) {
        this.companies = companies;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", project_description='" + project_description + '\'' +
                '}';
    }
}
