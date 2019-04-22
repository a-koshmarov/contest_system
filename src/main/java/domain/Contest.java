package domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Contest {
    private Long id;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private Collection<Contestant> contestantById;
    private Collection<Problem> problemById;

    @Id
    @Column(name = "ID", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "startTime", nullable = true, length = -1)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime", nullable = true, length = -1)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @OneToMany(mappedBy = "contestsByContestId")
    public Collection<Contestant> getContestantById() {
        return contestantById;
    }

    public void setContestantById(Collection<Contestant> contestantById) {
        this.contestantById = contestantById;
    }

    @OneToMany(mappedBy = "contestsByContestId")
    public Collection<Problem> getProblemById() {
        return problemById;
    }

    public void setProblemById(Collection<Problem> problemById) {
        this.problemById = problemById;
    }
}
