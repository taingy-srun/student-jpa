package entities;

import entities.course.Course;
import entities.course.OnCampusCourse;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
@NamedQuery(name = "Student.canGraduate", query = "SELECT s FROM Student s WHERE s.gpa >= :gpa and s.courseAttending is null GROUP BY s.id HAVING  COUNT(s.coursesAttended) >= :coursesAttended")
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private float gpa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_attending_id")
    private Course courseAttending;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Course> coursesAttended;

    public Student() {}

    public Student(String name, float gpa, OnCampusCourse courseAttending) {
        this.name = name;
        this.gpa = gpa;
        this.courseAttending = courseAttending;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Course getCourseAttending() {
        return courseAttending;
    }

    public void setCourseAttending(Course courseAttending) {
        this.courseAttending = courseAttending;
    }

    public List<Course> getCoursesAttended() {
        return coursesAttended;
    }

    public void setCoursesAttended(List<Course> coursesAttended) {
        this.coursesAttended = coursesAttended;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courseAttending=" + courseAttending +
                ", coursesAttended=" + coursesAttended +
                '}';
    }
}
