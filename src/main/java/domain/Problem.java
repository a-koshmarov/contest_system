package domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Problem {
    private Long id;
    private String name;
    private String description;
    private Collection<Attempt> attemptById;
    private Contest contestByContestId;

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

    @OneToMany(mappedBy = "problemsByProblemId")
    public Collection<Attempt> getAttemptById() {
        return attemptById;
    }

    public void setAttemptById(Collection<Attempt> attemptById) {
        this.attemptById = attemptById;
    }

    @ManyToOne
    @JoinColumn(name = "contestID", referencedColumnName = "ID")
    public Contest getContestByContestId() {
        return contestByContestId;
    }

    public void setContestByContestId(Contest contestByContestId) {
        this.contestByContestId = contestByContestId;
    }
}
