package domain.managers;

import domain.entities.Contest;
import domain.entities.Problem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ProblemManager{
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public List<Problem> getAllProblems(Contest contest) {
        return new ArrayList<>(contest.getProblemsById());
    }

    public Problem addProblem(Contest contest, String name, String description) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Problem problem = new Problem(name, description, contest);
        session.save(problem);
        contest.getProblemsById().add(problem);
        session.update(contest);
        session.getTransaction().commit();
        return problem;
    }

    public void removeProblem(Contest contest, Problem problem) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        contest.getProblemsById().remove(problem);
        session.update(contest);
        session.getTransaction().commit();
    }

    public void editProblemName(Problem problem, String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        problem.setName(name);
        session.update(problem);
        session.getTransaction().commit();
    }

    public void editProblemDescription(Problem problem, String description) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        problem.setDescription(description);
        session.update(problem);
        session.getTransaction().commit();
    }
}
