import entities.Student;
import entities.course.Course;
import entities.course.DistanceEducationCourse;
import entities.course.OnCampusCourse;
import entities.course.WebinarSessionDate;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("App Start");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        DistanceEducationCourse cs544DE = new DistanceEducationCourse("DE - Enterprise Architecture", Date.valueOf("2024-2-12"), "Najeeb", "Najeeb");
        WebinarSessionDate webinarSession1 = new WebinarSessionDate(Date.valueOf("2024-2-12"), cs544DE);
        WebinarSessionDate webinarSession2 = new WebinarSessionDate(Date.valueOf("2024-4-12"), cs544DE);
        List<WebinarSessionDate> webinarSessionDates = new ArrayList<>();
        webinarSessionDates.add(webinarSession1);
        webinarSessionDates.add(webinarSession2);
        cs544DE.setWebinarSessionDates(webinarSessionDates);
        tx.begin();
        em.persist(cs544DE);
        tx.commit();


        OnCampusCourse cs544 = new OnCampusCourse("EA");
        cs544.setCapacity(50);
        Student jim = new Student("Jim", 3.6f, cs544);
        tx.begin();
        em.persist(jim);
        tx.commit();

        String studentQueryStr = "SELECT s FROM Student s, OnCampusCourse c WHERE s.gpa > :gpa and c.capacity > :capacity";
        TypedQuery<Student> query = em.createQuery(studentQueryStr, Student.class);
        query.setParameter("gpa", 3.5);
        query.setParameter("capacity", 30);
        List<Student> students = query.getResultList();
        System.out.println("1. Printing students with GPA greater than 3.5 and attending a course with capacity > 30");
        students.forEach(System.out::println);
        System.out.println("End printing 1.");

        Student john = new Student("John", 3.5f, null);
        Course cs401 = new OnCampusCourse("Modern Programming Practices");
        Course cs435 = new OnCampusCourse("Algorithms");
        Course cs516 = new OnCampusCourse("Cloud Computing");
        Course cs472 = new OnCampusCourse("Web Programming");
        Course cs572 = new OnCampusCourse("Modern Web Applications");
        Course cs422 = new OnCampusCourse("Database Management Systems");
        Course cs489 = new OnCampusCourse("Applied Software Development");
        Course cs590 = new OnCampusCourse("Software Architecture");
        Course cs545 = new OnCampusCourse("Web Application Architecture");
        List<Course> attendedCourses = new ArrayList<>();
        attendedCourses.add(cs401);
        attendedCourses.add(cs435);
        attendedCourses.add(cs516);
        attendedCourses.add(cs572);
        attendedCourses.add(cs472);
        attendedCourses.add(cs422);
        attendedCourses.add(cs489);
        attendedCourses.add(cs590);
        attendedCourses.add(cs545);
        john.setCoursesAttended(attendedCourses);
        tx.begin();
        em.persist(john);
        tx.commit();

        TypedQuery<Student> studentCanGraduateQuery = em.createNamedQuery("Student.canGraduate", Student.class);
        studentCanGraduateQuery.setParameter("gpa", 3.0);
        studentCanGraduateQuery.setParameter("coursesAttended", 9);
        List<Student> studentCanGraduateList = studentCanGraduateQuery.getResultList();
        System.out.println("2. Printing student can graduate");
        studentCanGraduateList.forEach(System.out::println);
        System.out.println("End printing 2.");


        john.setGpa(2.9f);
        john.setCourseAttending(cs544DE);
        tx.begin();
        em.persist(john);
        tx.commit();

        CriteriaBuilder criBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriQuery = criBuilder.createQuery(Student.class);
        Root<Student> studentRoot = studentCriQuery.from(Student.class);
        studentCriQuery.select(studentRoot);

        Predicate gpaPredicate = criBuilder.lessThan(studentRoot.get("gpa"), 3.0);

        Join<Student, DistanceEducationCourse> joinCourse = studentRoot.join("courseAttending");
        Predicate professorPredicate = criBuilder.equal(joinCourse.get("professorName"), "Najeeb");

        Predicate andPredicate = criBuilder.and(gpaPredicate, professorPredicate);
        studentCriQuery.where(andPredicate);

        TypedQuery<Student> studentQuery = em.createQuery(studentCriQuery);
        List<Student> studentList = studentQuery.getResultList();
        System.out.println("3. Printing students with GPA less than 3.0 and attending a DE course with \"Najeeb\"");
        studentList.forEach(System.out::println);
        System.out.println("End printing 3.");


        em.close();
        emf.close();

        System.out.println("App End");
    }
}
