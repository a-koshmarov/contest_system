import domain.entities.Attempt;
import domain.entities.Contest;
import domain.entities.Contestant;
import domain.entities.Problem;
import domain.managers.AdminManager;
import domain.managers.AuthManager;
import domain.managers.ContestantManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ContestantManager manager = new ContestantManager();
        AdminManager adminManager = new AdminManager();
        List<Contest> contests = adminManager.getContestManager().getContests();
        Contest contest = contests.get(0);
        List<Problem> problems = new ArrayList<>(contest.getProblemsById());
        Problem problem = problems.get(0);
//
        Contestant contestant = adminManager.getContestManager().addContestant(contest,"joja", "1234d");
//        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Contestant contestant = new Contestant("bobDylan", "12345");
//        session.save(contestant);
//        session.getTransaction().commit();
//        Attempt attempt = manager.getAttemptManager().submitAttempt(problem, "{12}", "java", contestant);
//        AuthManager manager = new AuthManager();
//        Contestant contestant = manager.getContestant("bobDylan", "12345");

        HibernateSessionFactory.shutdown();
    }
}
