package Main.Utils;

import Main.Main;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {
    private SessionFactory sessionFactory;
    private static Session session;

    public void startSessionFactory(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static Session getSession(){
        return session;
    }

    public void closeSessionFactory(){
        session.close();
        sessionFactory.close();
    }


}
