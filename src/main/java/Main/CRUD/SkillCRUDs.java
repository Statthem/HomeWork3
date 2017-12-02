package Main.CRUD;

import Main.ConsoleApp.ConsoleApp;
import Main.DAO.Classes.DevelopersDAO;
import Main.DAO.Classes.ProjectsDAO;
import Main.DAO.Classes.SkillsDAO;
import Main.Entity.Developers;
import Main.Entity.Skills;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class SkillCRUDs {
    ConsoleApp consoleApp;
    DevelopersDAO developersDAO = new DevelopersDAO();
    SkillsDAO skillsDAO = new SkillsDAO();
    ProjectsDAO projectsDAO = new ProjectsDAO();


    public void createSkill(Skills skills){
        consoleApp = new ConsoleApp();
        skills = getSkillIfExists();
        if (skills.getId() != 0)
            System.out.println("такой уже существует!");
        else{
            //предлагаем создать девелопера
            System.out.println("Хотите ли вы добавить девелопера?  (Y/N)");
            String answer = consoleApp.consoleReaderEmpty();
            if(answer.equals("Y")){
                Developers developers = getDeveloperIfExists();
                if(developers.getId() != 0) {

                    Set<Developers> set = new HashSet<>();
                    set.add(developers);


                    skills.setDevelopers(set);
                    //создаем девелопера
                    System.out.println( developers.getSkills().toString());
                    //<>

                    skillsDAO.create(skills);
                }
                else{
                    skillsDAO.create(skills);
                    //Нативным Sql добавляем связь в сводную таблицу(даюы избежать повторений)
                    developersDAO.create(developers);
                    long id = developersDAO.exists(developers);
                    String sql = "Insert into developers_skills Values('" + id + "','" + skills.getId() + "')";
                    Transaction t = null;
                    try {
                        t = SessionUtils.getSession().beginTransaction();
                        SQLQuery query = SessionUtils.getSession().createSQLQuery(sql);
                        query.executeUpdate();
                        t.commit();
                    } catch (HibernateException e) {
                        e.printStackTrace();
                        t.rollback();
                    }
                }

            }else{
                skillsDAO.create(skills);
            }

        }
    }

    public void readSkill(Skills skills){
        consoleApp = new ConsoleApp();
        skills = getSkillIfExists();
        if (skills.getId() != 0) {
            skills = skillsDAO.read(skills);
            System.out.println(skills.toString());
        }   else System.out.println("такого не существует!");
    }

    public void updateSkill(Skills skills){
        consoleApp = new ConsoleApp();
        skills = getSkillIfExists();
        if (skills.getId() != 0) {

            System.out.println("Введите новое название скила");
            String skill = consoleApp.consoleReader();


            skills.setSkill(skill);

            skillsDAO.update(skills);
        }
        else System.out.println("такого не существует!");

    }

    public void deleteSkill(Skills skills){
        consoleApp = new ConsoleApp();
        skills = getSkillIfExists();
        if (skills.getId() != 0)
            skillsDAO.delete(skills);
        else System.out.println("такого не существует!");
    }

    public Developers getDeveloperIfExists(){
        consoleApp = new ConsoleApp();
        System.out.println("Введите имя девелопера");
        String first_name = consoleApp.consoleReader();
        System.out.println("Введите фамилию девелопера");
        String second_name = consoleApp.consoleReader();

        Developers developers = new Developers(first_name,second_name);
        long id = developersDAO.exists(developers);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            developers.setId(id);
        }
        return developers;
    }

    public Skills getSkillIfExists(){
        System.out.println("Введите название скилла");
        String skill = consoleApp.consoleReader();


        Skills skills = new Skills(skill);
        long id = skillsDAO.exists(skills);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            skills.setId(id);
        }
        return skills;
    }
}

