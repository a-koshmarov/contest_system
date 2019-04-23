package domain.testing;

import domain.entities.Attempt;

import java.util.HashMap;
import java.util.Map;

public class Tester {
    private TestSet testSet = new TestSet();
    private Map<String, Compiler> compilerMap = new HashMap<>();
    private Map<Integer, String> returnCodes = new HashMap<>();
    private Map<Integer, String> testCodes = new HashMap<>();

    public Tester(){
        compilerMap.put("java", new JavaCompiler());

        returnCodes.put(0, "0: Compilation successful");
        returnCodes.put(-1, "-1: Compilation Failed");

        testCodes.put(0, "0: Tests passed");
        testCodes.put(-1, "-1: Compilation error");
        testCodes.put(2, "1: Memory limit exceeded");
        testCodes.put(3, "2: Time limit exceeded");
        testCodes.put(4, "4: Tests failed");
    }

    public Attempt testAttempt(Attempt attempt){
        ExecAttempt executable = compileAttempt(attempt);
        if (executable.getReturnCode()==0){
            int testCode = executable.run(testSet);
            attempt.setTestCode(testCodes.get(testCode));
            if (testCode==0){
                attempt.setAccepted((short)1);
            }
        } else {
            attempt.setTestCode(testCodes.get(-1));
        }

        return attempt;
    }

    private ExecAttempt compileAttempt(Attempt attempt){
        String compiler = attempt.getCompiler();
        ExecAttempt executable = compilerMap.get(compiler).compile(attempt);
        if (executable.getReturnCode() == 0){
            attempt.setReturnCode(returnCodes.get(0));
        } else {
            attempt.setReturnCode(returnCodes.get(-1));
        }
        return executable;
    }



}
