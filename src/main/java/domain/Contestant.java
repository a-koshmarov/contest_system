package domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Contestant {
    private Long id;
    private String userName;
    private String password;
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
    @Column(name = "userName", nullable = true, length = -1)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "contestantsByContestantId")
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
