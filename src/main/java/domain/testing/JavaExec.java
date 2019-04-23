package domain.testing;

public class JavaExec implements ExecAttempt {
    private int returnCode;
    private String compiled;

    JavaExec(int returnCode, String compiled){
        this.returnCode = returnCode;
        this.compiled = compiled;
    }

    @Override
    public int run(TestSet testSet) {
        long countOne = compiled.chars().filter(ch -> ch == '1').count();
        long countTwo = compiled.chars().filter(ch -> ch == '2').count();
        long countThree = compiled.chars().filter(ch -> ch == '3').count();
        long countFour = compiled.chars().filter(ch -> ch == '4').count();
        if (countOne > 1){
            return 1;
        } else if (countTwo > 2){
            return 2;
        } else if (countThree > 3){
            return 3;
        } else if (countFour > 4){
            return 4;
        } else {
            return 0;
        }
    }

    @Override
    public int getReturnCode() {
        return returnCode;
    }
}
