package Main.CRUD;

import Main.ConsoleApp.ConsoleApp;
import Main.DAO.Classes.CompaniesDAO;
import Main.DAO.Classes.DevelopersDAO;
import Main.DAO.Classes.ProjectsDAO;
import Main.DAO.Classes.SkillsDAO;
import Main.Entity.Companies;
import Main.Entity.Developers;
import Main.Entity.Projects;
import Main.Entity.Skills;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CompanieCRUDs {
    ConsoleApp consoleApp;
   CompaniesDAO companiesDAO = new CompaniesDAO();
    ProjectsDAO projectsDAO = new ProjectsDAO();


    public void createCompanie(Companies companies){
        consoleApp = new ConsoleApp();
        companies = getCompanieIfExists();
        if (companies.getId() != 0)
            System.out.println("такой уже существует!");
        else{
            //предлагаем создать девелопера
            System.out.println("Хотите ли вы добавить проєкт?  (Y/N)");
            String answer = consoleApp.consoleReaderEmpty();
            if(answer.equals("Y")){
                Projects projects = getProjectIfExists();
                if(projects.getId() != 0) {

                    Set<Projects> set = new HashSet<>();
                    set.add(projects);


                    companies.setProjects(set);
                    //создаем девелопера
                    //System.out.println( developers.getSkills().toString());
                    //<>

                    companiesDAO.create(companies);
                }
                else{
                    companiesDAO.create(companies);
                    //Нативным Sql добавляем связь в сводную таблицу(даюы избежать повторений)
                    projectsDAO.create(projects);
                    long id = projectsDAO.exists(projects);
                    String sql = "Insert into companies_projects Values('" + id + "','" + companies.getId() + "')";
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
               companiesDAO.create(companies);
            }

        }
    }

    public void readCompanie(Companies companies){
        consoleApp = new ConsoleApp();
        companies = getCompanieIfExists();
        if (companies.getId() != 0) {
            companies = companiesDAO.read(companies);
            System.out.println(companies.toString());
        }   else System.out.println("такого не существует!");
    }

    public void updateCompanie(Companies companies){
        consoleApp = new ConsoleApp();
        companies = getCompanieIfExists();
        if (companies.getId() != 0) {

            System.out.println("Введите новое имя компании");
            String companie_name = consoleApp.consoleReader();


            companies.setCompanie_name(companie_name);

            companiesDAO.update(companies);
        }
        else System.out.println("такого не существует!");

    }

    public void deleteCompanies(Companies companies){
        consoleApp = new ConsoleApp();
        companies = getCompanieIfExists();
        if (companies.getId() != 0)
            companiesDAO.delete(companies);
        else System.out.println("такого не существует!");
    }

    public Companies getCompanieIfExists(){
        System.out.println("Введите название компании");
        String company_name = consoleApp.consoleReader();


        Companies companies= new Companies(company_name);
        long id = companiesDAO.exists(companies);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            companies.setId(id);
        }
        return companies;
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
