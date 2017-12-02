package Main.CRUD;

import Main.ConsoleApp.ConsoleApp;
import Main.DAO.Classes.CustomersDAO;
import Main.DAO.Classes.DevelopersDAO;
import Main.DAO.Classes.ProjectsDAO;
import Main.DAO.Classes.SkillsDAO;
import Main.Entity.Customers;
import Main.Entity.Developers;
import Main.Entity.Projects;
import Main.Entity.Skills;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CustomerCRUDs {
    ConsoleApp consoleApp;
    DevelopersDAO developersDAO = new DevelopersDAO();
    SkillsDAO skillsDAO = new SkillsDAO();
    ProjectsDAO projectsDAO = new ProjectsDAO();
    CustomersDAO customersDAO = new CustomersDAO();


    public void createCustomer(Customers customers){
        consoleApp = new ConsoleApp();
        customers = getCustomersIfExists();
        if (customers.getId() != 0)
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


                    customers.setProjects(set);
                    //создаем девелопера
                    //System.out.println( developers.getSkills().toString());
                    //<>

                    customersDAO.create(customers);
                }
                else{
                    customersDAO.create(customers);
                    //Нативным Sql добавляем связь в сводную таблицу(даюы избежать повторений)
                    projectsDAO.create(projects);
                    long id = projectsDAO.exists(projects);
                    String sql = "Insert into companies_projects Values('" + id + "','" + customers.getId() + "')";
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
                customersDAO.create(customers);
            }

        }
    }

    public void readCustomer(Customers customers){
        consoleApp = new ConsoleApp();
        customers = getCustomersIfExists();
        if (customers.getId() != 0) {
            customers = customersDAO.read(customers);
            System.out.println(customers.toString());
        }   else System.out.println("такого не существует!");
    }

    public void updateCustomer(Customers customers){
        consoleApp = new ConsoleApp();
        customers = getCustomersIfExists();
        if (customers.getId() != 0) {

            System.out.println("Введите новое имя клиента");
            String customer_name = consoleApp.consoleReader();


            customers.setCostumer_name(customer_name);

            customersDAO.update(customers);
        }
        else System.out.println("такого не существует!");

    }

    public void deleteCustomer(Customers customers){
        consoleApp = new ConsoleApp();
        customers = getCustomersIfExists();
        if (customers.getId() != 0)
            customersDAO.delete(customers);
        else System.out.println("такого не существует!");
    }

    public Customers getCustomersIfExists(){
        System.out.println("Введите имя клиента");
        String customer_name = consoleApp.consoleReader();


        Customers customers= new Customers(customer_name);
        long id = customersDAO.exists(customers);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            customers.setId(id);
        }
        return customers;
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
