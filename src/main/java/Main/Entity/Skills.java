package Main.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SKILLS", uniqueConstraints = {
@UniqueConstraint(columnNames = "skill")})
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "SKILL")
    private String skill;


    public Skills(String skill, Set<Developers> developers) {
        this.skill = skill;
        this.developers = developers;
    }
    public Skills() {

    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "developers_skills", joinColumns = {@JoinColumn(name = "skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "developer_id")})
    private Set<Developers> developers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Skills(String skill) {
        this.skill = skill;
    }

    public Set<Developers> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developers> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", skill='" + skill.toString() + '\'' +
                '}';
    }
}
