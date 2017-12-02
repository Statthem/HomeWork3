package Main.DAO.Classes;

import Main.DAO.Interfaces.IDevelopersDAO;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class DevelopersDAO<Developers> implements IDevelopersDAO {
    @Override
    public void create(Main.Entity.Developers developers) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().save(developers);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }


    @Override
    public void delete(Main.Entity.Developers developers) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().delete(developers);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void update(Main.Entity.Developers developers) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().update(developers);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public Main.Entity.Developers read(Main.Entity.Developers developers) {
        long id = developers.getId();
        Main.Entity.Developers developer = null;
        try {
            Query query = SessionUtils.getSession().createQuery("Select d from Developers d Where d.id =:id");
            query.setParameter("id", id);
            developer = (Main.Entity.Developers) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return developer;
    }


    @Override
    public long exists(Main.Entity.Developers developers) {
        long developerId = 0;
        try {
            Query query = SessionUtils.getSession().createQuery("Select d.id from Developers d Where d.first_name =:fName and d.second_name =:sName");
            query.setParameter("fName", developers.getFirst_name());
            query.setParameter("sName", developers.getSecond_name());
            developerId = (long) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }catch (NullPointerException e){ }

        return developerId;
    }
}