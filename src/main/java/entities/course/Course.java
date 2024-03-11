package entities.course;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "courses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "C_TYPE")
public abstract class Course {
    @Id
    @GeneratedValue
    private int id;
    private String title;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "professor_name")
    private String professorName;

    public Course() {}

    public Course(String title) {
        this.title = title;
    }

    public Course(String title, Date startDate, String professorName) {
        this.title = title;
        this.startDate = startDate;
        this.professorName = professorName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", professorName='" + professorName + '\'' +
                '}';
    }
}
