package domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Problem {
    private Long id;
    private String name;
    private String description;
    private Collection<Attempt> attemptsById;
    private Contest contestsByContestId;

    public Problem() {
    }

    public Problem(String name, String description, Contest contestsByContestId) {
        this.name = name;
        this.description = description;
        this.contestsByContestId = contestsByContestId;
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
