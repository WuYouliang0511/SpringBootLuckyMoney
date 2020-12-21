package com.example.demo.locationLabel;

public class Warning {

    private String way;
    private String type;

    public Warning(String way, String type) {
        this.way = way;
        this.type = type;
    }

    public Warning() {
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
