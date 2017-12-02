package Main.DAO.Classes;

import Main.DAO.Interfaces.ICustomersDAO;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class CustomersDAO<Customer> implements ICustomersDAO {
    @Override
    public void create(Main.Entity.Customers customers) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().save(customers);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }


    @Override
    public void delete(Main.Entity.Customers customers) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().delete(customers);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void update(Main.Entity.Customers customers) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().update(customers);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public Main.Entity.Customers read(Main.Entity.Customers customers) {
        long id = customers.getId();
        Main.Entity.Customers customer = null;
        try {
            Query query = SessionUtils.getSession().createQuery("Select c from Customers c Where c.id =:id");
            query.setParameter("id", id);
            customer = (Main.Entity.Customers) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return customers;
    }


    @Override
    public long exists(Main.Entity.Customers customers) {
        long customerId = 0;
        try {
            Query query = SessionUtils.getSession().createQuery("Select c.id from Customers c Where c.costumer_name =:name");
            query.setParameter("name", customers.getCostumer_name());
            customerId = (long) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }catch (NullPointerException e){ }


        return customerId;
    }
}


