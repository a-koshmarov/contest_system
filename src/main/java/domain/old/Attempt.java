package domain.old;

import javax.persistence.*;

@Entity
public class Attempt {
    private Long id;
    private String text;
    private String returnCode;
    private String compiler;
    private Short accepted;
    private Problem problemByProblemId;
    private Contestant contestantByContestantId;

    @Id
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
    public Problem getProblemByProblemId() {
        return problemByProblemId;
    }

    public void setProblemByProblemId(Problem problemByProblemId) {
        this.problemByProblemId = problemByProblemId;
    }

    @ManyToOne
    @JoinColumn(name = "contestantID", referencedColumnName = "ID")
    public Contestant getContestantByContestantId() {
        return contestantByContestantId;
    }

    public void setContestantByContestantId(Contestant contestantByContestantId) {
        this.contestantByContestantId = contestantByContestantId;
    }
}
