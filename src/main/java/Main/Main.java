package Main;

import Main.DAO.Classes.CompaniesDAO;
import Main.Entity.Companies;
import Main.Entity.Developers;
import Main.Entity.Projects;
import Main.Entity.Skills;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        utils.startSessionFactory();

        Session session = Utils.getSession();

        Set<Projects> skills = new HashSet<>();
        skills.add(new Projects("pro","lol"));
        Developers developer = new Developers("alex", "Statthem");
        developer.setProjects(skills);


        CompaniesDAO dao = new CompaniesDAO();

        Companies companies = new Companies("looc");
        companies.setId(2);
        Companies newCompany = dao.read(companies);
        System.out.println(newCompany.toString());







    }
}
