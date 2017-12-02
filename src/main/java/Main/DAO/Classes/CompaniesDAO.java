package Main.DAO.Classes;

import Main.DAO.Interfaces.ICompaniesDAO;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Transaction;

public class CompaniesDAO<Companies,Long> implements ICompaniesDAO {
    @Override
    public void create(Main.Entity.Companies companies) {
                Transaction t = null;
                try {
                    t = SessionUtils.getSession().beginTransaction();
                    SessionUtils.getSession().save(companies);
                    t.commit();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    t.rollback();
                }
            }


    @Override
    public void delete(Main.Entity.Companies companies) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().delete(companies);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void update(Main.Entity.Companies companies) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().update(companies);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public Main.Entity.Companies read(Main.Entity.Companies companies) {
        long id = companies.getId();
        Main.Entity.Companies companie = null;
        try {
            Query query = SessionUtils.getSession().createQuery("Select c from Companies c Where c.id =:id");
            query.setParameter("id", id);
            companie = (Main.Entity.Companies) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return companie;
    }


    @Override
    public long exists(Main.Entity.Companies companies) {
        long companieId = 0;
        try {
            Query query = SessionUtils.getSession().createQuery("Select c.id from Companies c Where c.companie_name =:name");
            query.setParameter("name", companies.getCompanie_name());
            companieId = (long) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }catch (NullPointerException e){ }

     //   System.out.println("companieId = " + companieId);
        return companieId;
    }


    }

