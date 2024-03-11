package entities.course;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "webinar_session_dates")
public class WebinarSessionDate {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "webinar_session_date")
    private Date webinarSessionDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public WebinarSessionDate() {
    }

    public WebinarSessionDate(Date webinarSessionDate, Course course) {
        this.webinarSessionDate = webinarSessionDate;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public Date getWebinarSessionDate() {
        return webinarSessionDate;
    }

    public void setWebinarSessionDate(Date webinarSessionDate) {
        this.webinarSessionDate = webinarSessionDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
