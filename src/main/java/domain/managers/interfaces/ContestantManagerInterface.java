package domain.managers.interfaces;

import domain.entities.Attempt;
import domain.entities.Contest;
import domain.entities.Contestant;
import domain.entities.Problem;

import java.util.List;

public interface ContestantManagerInterface {
    public List<Attempt> getAllUserAttempts(Problem problem, Contestant user);
    public Attempt submitAttempt(Problem problem, String attemptText, String compiler, Contestant user);
    public List<Problem> getAllProblems(Contest contest);
    public Contest getUserContest(Contestant user);
}
