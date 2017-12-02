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
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class ProjectCRUDs {
    ConsoleApp consoleApp;
    DevelopersDAO developersDAO = new DevelopersDAO();
    SkillsDAO skillsDAO = new SkillsDAO();
    ProjectsDAO projectsDAO = new ProjectsDAO();


    public void createProject(Projects projects){
        consoleApp = new ConsoleApp();
        projects = getProjectIfExists();
        if (projects.getId() != 0)
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


                    projects.setDevelopers(set);
                    //создаем девелопера
                    System.out.println( developers.getSkills().toString());
                    //<>

                    projectsDAO.create(projects);
                }
                else{
                    projectsDAO.create(projects);
                    //Нативным Sql добавляем связь в сводную таблицу(даюы избежать повторений)
                    developersDAO.create(developers);
                    long id = developersDAO.exists(developers);
                    String sql = "Insert into developers_projects Values('" + id + "','" + projects.getId() + "')";
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
                projectsDAO.create(projects);
            }

        }
    }

    public void readProject(Projects projects){
        consoleApp = new ConsoleApp();
        projects = getProjectIfExists();
        if (projects.getId() != 0) {
            projects = projectsDAO.read(projects);
            System.out.println(projects.toString());
        }   else System.out.println("такого не существует!");
    }

    public void updateProject(Projects projects){
        consoleApp = new ConsoleApp();
        projects = getProjectIfExists();
        if (projects.getId() != 0) {

            System.out.println("Введите новое название проєкта");
            String project_name = consoleApp.consoleReader();
            System.out.println("Введите новое описание проєкта");
            String project_description = consoleApp.consoleReader();

            projects.setProject_name(project_name);
            projects.setProject_description(project_description);

            projectsDAO.update(projects);
        }
        else System.out.println("такого не существует!");

    }

    public void deleteProject(Projects projects){
        consoleApp = new ConsoleApp();
        projects = getProjectIfExists();
        if (projects.getId() != 0)
            projectsDAO.delete(projects);
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

    public Projects getProjectIfExists(){
        System.out.println("Введите название проэкта");
        String project_name = consoleApp.consoleReader();

        System.out.println("Введите описание проэкта");
        String project_description = consoleApp.consoleReader();



        Projects projects = new Projects(project_name,project_description);
        long id = projectsDAO.exists(projects);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            projects.setId(id);
        }
        return projects;
    }
}


