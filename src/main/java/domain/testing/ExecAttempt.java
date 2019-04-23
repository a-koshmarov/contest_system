package domain.testing;

public interface ExecAttempt {
    public int run(TestSet testSet);
    public int getReturnCode();
}
