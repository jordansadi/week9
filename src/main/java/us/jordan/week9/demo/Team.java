package us.jordan.week9.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    private String name;
    private String location;

    public Team(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Team() {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}