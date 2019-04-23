package domain.testing;

import domain.entities.Attempt;

public interface Compiler {
    public ExecAttempt compile(Attempt attempt);
}
