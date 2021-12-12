package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamEntity {

    @Id
    private String name;

    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
