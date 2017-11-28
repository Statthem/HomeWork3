package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utils {
    private SessionFactory sessionFactory;
    private static Session session;

    public void startSessionFactoryAndSession(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static Session getSession(){
        return session;
    }
}
