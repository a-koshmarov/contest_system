package domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Contestant {
    private Long id;
    private String userName;
    private String password;
    private Collection<Attempt> attemptsById;
    private Contest contestsByContestId;

    public Contestant() {
    }

    public Contestant(String userName, String password, Contest contest) {
        this.userName = userName;
        this.password = password;
        this.contestsByContestId = contest;
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
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Attempt> getAttemptsById() {
        return attemptsById;
    }

    public void setAttemptsById(Collection<Attempt> attemptsById) {
        this.attemptsById = attemptsById;
    }

    @ManyToOne
    @JoinColumn(name = "contestID", referencedColumnName = "ID")
    public Contest getContestsByContestId() {
        return contestsByContestId;
    }

    public void setContestsByContestId(Contest contestsByContestId) {
        this.contestsByContestId = contestsByContestId;
    }
}
