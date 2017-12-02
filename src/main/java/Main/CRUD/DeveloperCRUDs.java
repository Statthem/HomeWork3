package Main.CRUD;

import Main.ConsoleApp.ConsoleApp;
import Main.DAO.Classes.DevelopersDAO;
import Main.DAO.Classes.ProjectsDAO;
import Main.DAO.Classes.SkillsDAO;
import Main.Entity.Developers;
import Main.Entity.Projects;
import Main.Entity.Skills;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.sql.SQLSyntaxErrorException;
import java.util.HashSet;
import java.util.Set;

public class DeveloperCRUDs {
    ConsoleApp consoleApp;
    DevelopersDAO developersDAO = new DevelopersDAO();
    SkillsDAO skillsDAO = new SkillsDAO();
    ProjectsDAO projectsDAO = new ProjectsDAO();


    public void createDeveloper(Developers developers){
        consoleApp = new ConsoleApp();
        developers = getDeveloperIfExists();
        if (developers.getId() != 0)
            System.out.println("такой уже существует!");
        else{
            //предлагаем создать скил
            System.out.println("Хотите ли вы добавить скилл?  (Y/N)");
            String answer = consoleApp.consoleReaderEmpty();
            if(answer.equals("Y")){
                Skills skills = consoleApp.getSkillIfExists();
                if(skills.getId() != 0) {

                    Set<Skills> set = new HashSet<Skills>();
                    set.add(skills);


                    developers.setSkills(set);
                    //создаем девелопера
                    System.out.println( developers.getSkills().toString());
                    //<>

                    developersDAO.create(developers);
                }
                else{
                    developersDAO.create(developers);
                    //Нативным Sql добавляем связь в сводную таблицу(даюы избежать повторений)
                    skillsDAO.create(skills);
                    long id = skillsDAO.exists(skills);
                    String sql = "Insert into developers_skills Values('" + developers.getId() + "','" + id + "')";
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
                developersDAO.create(developers);
            }

        }
    }

    public void readDeveloper(Developers developers){
        consoleApp = new ConsoleApp();
        developers = getDeveloperIfExists();
        if (developers.getId() != 0) {
            developers = developersDAO.read(developers);
            System.out.println(developers.toString());
        }   else System.out.println("такого не существует!");
    }

    public void updateDeveloper(Developers developers){
        consoleApp = new ConsoleApp();
        developers = getDeveloperIfExists();
        if (developers.getId() != 0) {

            System.out.println("Введите новое имя девелопера");
            String first_name = consoleApp.consoleReader();
            System.out.println("Введите новое фамилию девелопера");
            String second_name = consoleApp.consoleReader();

            developers.setFirst_name(first_name);
            developers.setSecond_name(second_name);

            developersDAO.update(developers);
        }
        else System.out.println("такого не существует!");

    }

    public void deleteDeveloper(Developers developers){
        consoleApp = new ConsoleApp();
        developers = getDeveloperIfExists();
        if (developers.getId() != 0)
            developersDAO.delete(developers);
        else System.out.println("такого не существует!");
        developers = null;
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

}
