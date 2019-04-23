package domain.managers;

import domain.entities.Admin;
import domain.entities.Contest;
import domain.entities.Contestant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utility.HibernateSessionFactory;

import java.util.List;

public class AuthManager {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public Contestant getContestant(String userName, String password){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Contestant> contestants = session.createCriteria(Contestant.class)
                .add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", password))
                .list();
        session.getTransaction().commit();
        return contestants.get(0);
    }

    public Admin getAdmin(String userName, String password){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Admin> admins = session.createCriteria(Admin.class)
                .add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", password))
                .list();
        session.getTransaction().commit();
        return admins.get(0);
    }
}
