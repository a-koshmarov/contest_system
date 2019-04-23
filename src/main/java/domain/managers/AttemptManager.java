package domain.managers;

import domain.entities.Attempt;
import domain.entities.Contestant;
import domain.entities.Problem;
import domain.testing.Tester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateSessionFactory;

import java.util.List;

public class AttemptManager {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Tester tester = new Tester();

    public List<Attempt> getAllAttempts(Problem problem) {
        return null;
    }

    public List<Attempt> getUserAttempts(Problem problem, Contestant user) {
        return null;
    }

    public Attempt submitAttempt(Problem problem, String attemptText, String compiler, Contestant user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Attempt attempt = new Attempt(attemptText, compiler, problem, user);
        attempt = tester.testAttempt(attempt);
        session.save(attempt);
        problem.getAttemptsById().add(attempt);
        session.update(problem);
        session.getTransaction().commit();
        return attempt;
    }
}
