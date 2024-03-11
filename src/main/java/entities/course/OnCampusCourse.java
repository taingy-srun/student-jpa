package entities.course;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
@DiscriminatorValue(value = "ON-CAMPUS")
public class OnCampusCourse extends Course {

    private String room;
    private int capacity;

    public OnCampusCourse(String title, Date startDate, String professorName, String room, int capacity) {
        super(title, startDate, professorName);
        this.room = room;
        this.capacity = capacity;
    }

    public OnCampusCourse(String title) {
        super(title);
    }

    public OnCampusCourse() {
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "OnCampusCourse{" +
                "room='" + room + '\'' +
                ", capacity=" + capacity +
                "} " + super.toString();
    }
}
