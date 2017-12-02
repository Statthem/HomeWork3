package Main.DAO.Classes;

import Main.DAO.Interfaces.IProjectsDAO;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class ProjectsDAO<Projects> implements IProjectsDAO {
    @Override
    public void create(Main.Entity.Projects projects) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().save(projects);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }


    @Override
    public void delete(Main.Entity.Projects projects) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().delete(projects);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void update(Main.Entity.Projects projects) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().update(projects);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public Main.Entity.Projects read(Main.Entity.Projects projects) {
        long id = projects.getId();
        Main.Entity.Projects project = null;
        try {
            Query query = SessionUtils.getSession().createQuery("Select p from Projects p Where p.id =:id");
            query.setParameter("id", id);
            project = (Main.Entity.Projects) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return project;
    }


    @Override
    public long exists(Main.Entity.Projects projects) {
        long projectId = 0;
        try {
            Query query = SessionUtils.getSession().createQuery("Select p.id from Projects p Where p.project_name =:name");
            query.setParameter("name", projects.getProject_name());
            projectId = (long) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }catch (NullPointerException e){ }


        return projectId;
    }

}
