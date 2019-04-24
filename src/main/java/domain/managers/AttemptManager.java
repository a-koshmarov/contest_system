package domain.managers;

import domain.entities.Attempt;
import domain.entities.Contestant;
import domain.entities.Problem;
import domain.testing.Tester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utility.HibernateSessionFactory;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class AttemptManager {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Tester tester = new Tester();

    public List<Attempt> getAllAttempts(Problem problem) {
        return new ArrayList<>(problem.getAttemptsById());
    }

    public List<Attempt> getUserAttempts(Problem problem, Contestant user) {
        List<Attempt> userAttempts = new ArrayList<>();
        for(Attempt attempt: getAllAttempts(problem)){
            if(attempt.getContestantsByContestantId().getUserName().equals(user.getUserName())){
                userAttempts.add(attempt);
            }
        }
        return userAttempts ;
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
