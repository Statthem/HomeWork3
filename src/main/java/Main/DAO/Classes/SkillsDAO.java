package Main.DAO.Classes;

import Main.DAO.Interfaces.ISkillDAO;
import Main.Utils.SessionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class SkillsDAO<Skills> implements ISkillDAO {
    @Override
    public void create(Main.Entity.Skills skills) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().save(skills);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }


    @Override
    public void delete(Main.Entity.Skills skills) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().delete(skills);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void update(Main.Entity.Skills skills) {
        Transaction t = null;
        try {
            t = SessionUtils.getSession().beginTransaction();
            SessionUtils.getSession().update(skills);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public Main.Entity.Skills read(Main.Entity.Skills skills) {
        long id = skills.getId();
        Main.Entity.Skills skill = null;
        try {
            Query query = SessionUtils.getSession().createQuery("Select s from Skills s Where s.id =:id");
            query.setParameter("id", id);
            skill = (Main.Entity.Skills) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return skill;
    }


    @Override
    public long exists(Main.Entity.Skills skills) {
        long skillsId = 0;
        try {
            Query query = SessionUtils.getSession().createQuery("Select s.id from Skills s Where s.skill =:name");
            query.setParameter("name", skills.getSkill());
            skillsId = (long) query.setMaxResults(1).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }catch (NullPointerException e){ }


        return skillsId;
    }

}
