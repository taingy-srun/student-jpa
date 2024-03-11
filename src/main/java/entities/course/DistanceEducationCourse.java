package entities.course;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "DE")
public class DistanceEducationCourse extends Course {

    @Column(name = "exam_professor")
    private String examProfessor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST)
    private List<WebinarSessionDate> webinarSessionDates;


    public DistanceEducationCourse() {
    }

    public DistanceEducationCourse(String title, Date startDate, String professorName, String examProfessor) {
        super(title, startDate, professorName);
        this.examProfessor = examProfessor;
    }

    public String getExamProfessor() {
        return examProfessor;
    }

    public void setExamProfessor(String examProfessor) {
        this.examProfessor = examProfessor;
    }

    public List<WebinarSessionDate> getWebinarSessionDates() {
        return webinarSessionDates;
    }

    public void setWebinarSessionDates(List<WebinarSessionDate> webinarSessionDates) {
        this.webinarSessionDates = webinarSessionDates;
    }

    @Override
    public String toString() {
        return "DistanceEducationCourse{" +
                "examProfessor='" + examProfessor + '\'' +
                ", webinarSessionDates=" + webinarSessionDates +
                "} " + super.toString();
    }
}
