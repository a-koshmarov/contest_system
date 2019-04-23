package domain.managers;

import domain.entities.Contest;
import domain.entities.Contestant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateSessionFactory;

import java.util.List;

public class ContestManager {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private List<Contest> contests;

    public ContestManager() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        contests = session.createCriteria(Contest.class).list();
        session.getTransaction().commit();
    }

    public List<Contest> getContests() {
        return contests;
    }

    public Contest addContest(String name, String description, String startDate, String endDate) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Contest contest = new Contest(name, description, startDate, endDate);
        session.save(contest);
        session.getTransaction().commit();

        return contest;
    }

    public void cancelContest(Contest contest) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(contest);
        session.getTransaction().commit();
    }

    public void editContestName(Contest contest, String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        contest.setName(name);
        session.update(contest);
        session.getTransaction().commit();
    }

    public void editContestDescription(Contest contest, String description) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        contest.setDescription(description);
        session.update(contest);
        session.getTransaction().commit();
    }

    public void editContestDates(Contest contest, String start, String end) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        contest.setStartTime(start);
        contest.setEndTime(end);
        session.update(contest);
        session.getTransaction().commit();
    }

    public Contestant addContestant(Contest contest, String userName, String password) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Contestant contestant = new Contestant(userName, password, contest);
        session.save(contestant);
        contest.getContestantsById().add(contestant);
        session.update(contest);
        session.getTransaction().commit();
        return contestant;
    }

    public void removeContestant(Contest contest, Contestant contestant) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        contest.getContestantsById().remove(contestant);
        session.update(contest);
        session.getTransaction().commit();
    }
}
