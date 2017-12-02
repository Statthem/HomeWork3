package Main.ConsoleApp;

import Main.CRUD.*;
import Main.DAO.Classes.*;
import Main.Entity.*;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsoleApp {
    SessionUtils sessionUtils = new SessionUtils();
    DeveloperCRUDs developerCRUDs = new DeveloperCRUDs();
    SkillCRUDs skillCRUDs = new SkillCRUDs();
    ProjectCRUDs projectCRUDs = new ProjectCRUDs();
    CustomerCRUDs customerCRUDs = new CustomerCRUDs();
    CompanieCRUDs companieCRUDs = new CompanieCRUDs();

    CompaniesDAO companiesDAO = new CompaniesDAO();
    CustomersDAO customersDAO = new CustomersDAO();
    DevelopersDAO developersDAO = new DevelopersDAO();
    ProjectsDAO projectsDAO = new ProjectsDAO();
    SkillsDAO skillsDAO = new SkillsDAO();

    private static Scanner scanner = new Scanner(System.in);

    public static String consoleReader() {
        String str = null;

        boolean b = true;

        while (b) {
            str = scanner.nextLine();
            if (str.trim().length() != 0) {
                b = false;
            }
        }
        return str;
    }

    public static String consoleReaderEmpty() {
        String str = scanner.nextLine();
        return str;
    }


    public void consoleRead() {
        String operationdone = "Следующя операция\n";

        System.out.println("Закрытие програмы по команде 'end'\n" +
                "для просмотра списка команд введите 'help' \n" +
                "Програма запушена и ждет команды :");

        Developers developers = null;
        Skills skills = null;
        Projects projects = null;
        Companies companies = null;
        Customers customers = null;
        while (true) {
            String input = consoleReader();
            switch (input) {

                case "help":  helpInfo();
                    System.out.println(operationdone);
                               break;
                case "end":
                    try {
                        SessionUtils.getSession().clear();
                        sessionUtils.closeSessionFactory();
                        System.out.println("Успешно закрыто");
                    }catch (NullPointerException exc){  System.exit(0);}
                    System.exit(0);
                    break;

                case "D.developer":

                    developerCRUDs.deleteDeveloper(developers);
                    System.out.println(operationdone);
                    developers = null;
                    break;

                case "C.developer":

                    developerCRUDs.createDeveloper(developers);
                    System.out.println(operationdone);
                    developers = null;
                    break;
                case "U.developer":

                    developerCRUDs.updateDeveloper(developers);
                    System.out.println(operationdone);
                    developers = null;
                    break;

                case "R.developer":

                    developerCRUDs.readDeveloper(developers);
                    System.out.println(operationdone);
                    developers = null;
                    break;

                case "D.skill":

                    skillCRUDs.deleteSkill(skills);
                    System.out.println(operationdone);
                    skills = null;
                    break;

                case "C.skill":

                    skillCRUDs.createSkill(skills);
                    System.out.println(operationdone);
                    skills = null;
                    break;
                case "U.skill":

                    skillCRUDs.updateSkill(skills);
                    System.out.println(operationdone);
                    skills = null;
                    break;

                case "R.skill":

                    skillCRUDs.readSkill(skills);
                    System.out.println(operationdone);
                    skills = null;
                    break;

                case "D.project":

                    projectCRUDs.deleteProject(projects);
                    System.out.println(operationdone);
                    projects = null;
                    break;

                case "C.project":

                    projectCRUDs.createProject(projects);
                    System.out.println(operationdone);
                    projects = null;
                    break;
                case "U.project":

                    projectCRUDs.updateProject(projects);
                    System.out.println(operationdone);
                    projects = null;
                    break;

                case "R.project":

                    projectCRUDs.readProject(projects);
                    System.out.println(operationdone);
                    projects = null;
                    break;
                case "D.companie":

                    companieCRUDs.deleteCompanies(companies);
                    System.out.println(operationdone);
                    companies = null;
                    break;

                case "C.companie":

                    companieCRUDs.createCompanie(companies);
                    System.out.println(operationdone);
                    companies = null;
                    break;
                case "U.companie":

                    companieCRUDs.updateCompanie(companies);
                    System.out.println(operationdone);
                    companies = null;
                    break;

                case "R.companie":

                    companieCRUDs.readCompanie(companies);
                    System.out.println(operationdone);
                    companies = null;
                    break;
                case "D.customer":

                    customerCRUDs.deleteCustomer(customers);
                    System.out.println(operationdone);
                    customers = null;
                    break;

                case "C.customer":

                    customerCRUDs.createCustomer(customers);
                    System.out.println(operationdone);
                    customers = null;
                    break;
                case "U.customer":

                    customerCRUDs.updateCustomer(customers);
                    System.out.println(operationdone);
                    customers = null;
                    break;

                case "R.customer":

                    customerCRUDs.readCustomer(customers);
                    System.out.println(operationdone);
                    customers = null;
                    break;
                }
            }
        }



    public Skills getSkillIfExists(){
        System.out.println("Введите название скилла");
        String skill = consoleReader();


        Skills skills = new Skills(skill);
        long id = skillsDAO.exists(skills);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            skills.setId(id);
        }
        return skills;
    }

    public Companies getCompanieIfExists(){
        System.out.println("Введите название компании");
        String company_name = consoleReader();


        Companies companies= new Companies(company_name);
        long id = companiesDAO.exists(companies);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            companies.setId(id);
        }
        return companies;
    }

    public Customers getCustomersIfExists(){
        System.out.println("Введите имя клиента");
        String customer_name = consoleReader();


        Customers customers= new Customers(customer_name);
        long id = customersDAO.exists(customers);
        //  System.out.println("id  = "+ id);

        if (id != 0) {
            customers.setId(id);
        }
        return customers;
    }


    private void helpInfo() {
        System.out.println(
                "\nВсего есть есть четыри действия(CRUD)\n" +
                        "и пять сушностей (developer skill company customer project)\n" +
                        "Для вызова любого действия ипользуйте большую английськую" +
                        " заглавную букву ('C','R','U','D')\n" +
                        "далее через точку сущность \n\n" +
                        "Пример:\n" +
                        "'C.developer'"
        );
    }

}
