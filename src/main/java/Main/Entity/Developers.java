package Main.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEVELOPERS")
public class Developers {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "SECOND_NAME")
    private String second_name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "developers_skills", joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private Set<Skills> skills;

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "developers_projects", joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Projects> projects;

    public Developers(String first_name, String second_name) {
        this.first_name = first_name;
        this.second_name = second_name;

    }
    public Developers() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    @Override
    public String toString() {
        return "Developers{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", skills=" + skills +
                ", projects=" + projects + "\n" +
                '}';
    }
}
