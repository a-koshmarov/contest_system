package domain.entities;

import javax.persistence.*;

@Entity
public class Attempt {
    private Long id;
    private String text;
    private String returnCode;
    private String compiler;
    private String testCode;
    private Short accepted;
    private Problem problemsByProblemId;
    private Contestant contestantsByContestantId;

    public Attempt() {
    }

    public Attempt(String text, String compiler, Problem problemsByProblemId, Contestant contestantsByContestantId) {
        this.text = text;
        this.compiler = compiler;
        this.problemsByProblemId = problemsByProblemId;
        this.contestantsByContestantId = contestantsByContestantId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text", nullable = true, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "returnCode", nullable = true, length = -1)
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Basic
    @Column(name = "testCode", nullable = true, length = -1)
    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    @Basic
    @Column(name = "compiler", nullable = true, length = -1)
    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    @Basic
    @Column(name = "accepted", nullable = true)
    public Short getAccepted() {
        return accepted;
    }

    public void setAccepted(Short accepted) {
        this.accepted = accepted;
    }

    @ManyToOne
    @JoinColumn(name = "problemID", referencedColumnName = "ID")
    public Problem getProblemsByProblemId() {
        return problemsByProblemId;
    }

    public void setProblemsByProblemId(Problem problemsByProblemId) {
        this.problemsByProblemId = problemsByProblemId;
    }

    @ManyToOne
    @JoinColumn(name = "contestantID", referencedColumnName = "ID")
    public Contestant getContestantsByContestantId() {
        return contestantsByContestantId;
    }

    public void setContestantsByContestantId(Contestant contestantsByContestantId) {
        this.contestantsByContestantId = contestantsByContestantId;
    }
}
