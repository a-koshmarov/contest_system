package domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Contest {
    private Long id;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private Collection<Contestant> contestantsById;
    private Collection<Problem> problemsById;

    public Contest() {
    }

    public Contest(String name, String description, String startTime, String endTime) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
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
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Contestant> getContestantsById() {
        return contestantsById;
    }

    public void setContestantsById(Collection<Contestant> contestantsById) {
        this.contestantsById = contestantsById;
    }

    @OneToMany(mappedBy = "contestsByContestId")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Problem> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Collection<Problem> problemsById) {
        this.problemsById = problemsById;
    }

    @Override
    public String toString() {
        return name+" "+startTime+" "+endTime;
    }
}
