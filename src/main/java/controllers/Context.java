package controllers;

import domain.entities.Contest;
import domain.entities.Contestant;
import domain.entities.Problem;

public class Context {
    private static Contest currentContest;
    private static Problem currentProblem;
    private static Contestant currentContestant;

    public static Contest getCurrentContest() {
        return currentContest;
    }

    public static void setCurrentContest(Contest currentContest) {
        Context.currentContest = currentContest;
    }

    public static Problem getCurrentProblem() {
        return currentProblem;
    }

    public static void setCurrentProblem(Problem currentProblem) {
        Context.currentProblem = currentProblem;
    }

    public static Contestant getCurrentContestant() {
        return currentContestant;
    }

    public static void setCurrentContestant(Contestant currentContestant) {
        Context.currentContestant = currentContestant;
    }
}
