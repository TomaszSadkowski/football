package entity;

import javax.persistence.Entity;

public class LogosEntity {


    private String light;
    private String dark;

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getDark() {
        return dark;
    }

    public void setDark(String dark) {
        this.dark = dark;
    }
}
