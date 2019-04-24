package domain.testing;

import domain.entities.Attempt;

public class JavaCompiler implements Compiler {

    @Override
    public ExecAttempt compile(Attempt attempt) {
        String text = attempt.getText();
        long countOpen = text.chars().filter(ch -> ch == '{').count();
        long countClose = text.chars().filter(ch -> ch == '}').count();

        if (countOpen != countClose){
            return new JavaExec(-1, attempt.getText());
        } else {
            return new JavaExec(0, attempt.getText());
        }
    }

    @Override
    public String toString(){
        return "java";
    }
}
