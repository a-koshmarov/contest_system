package domain.managers;

import domain.managers.interfaces.ContestantManagerInterface;

public class ContestantManager {
    private ContestantManagerInterface attemptManager = new AdminManager();

    public ContestantManagerInterface getAttemptManager() {
        return attemptManager;
    }
}
