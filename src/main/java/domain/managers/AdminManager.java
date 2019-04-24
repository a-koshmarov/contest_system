package domain.managers;

import domain.entities.Attempt;
import domain.entities.Contest;
import domain.entities.Contestant;
import domain.entities.Problem;
import domain.managers.interfaces.ContestantManagerInterface;

import java.util.ArrayList;
import java.util.List;

public class AdminManager implements ContestantManagerInterface {
    private AttemptManager attemptManager = new AttemptManager();
    private ProblemManager problemManager = new ProblemManager();
    private ContestManager contestManager = new ContestManager();

    public AttemptManager getAttemptManager() {
        return attemptManager;
    }

    public ProblemManager getProblemManager() {
        return problemManager;
    }

    public ContestManager getContestManager() {
        return contestManager;
    }

    @Override
    public List<Attempt> getAllUserAttempts(Problem problem, Contestant user) {
        return attemptManager.getUserAttempts(problem,user) ;
    }

    @Override
    public Attempt submitAttempt(Problem problem, String attemptText, String compiler, Contestant user) {
        return attemptManager.submitAttempt(problem, attemptText, compiler, user);
    }

    @Override
    public List<Problem> getAllProblems(Contest contest) {
        return problemManager.getAllProblems(contest);
    }

    @Override
    public Contest getUserContest(Contestant user) {
        return user.getContestsByContestId();
    }
}
