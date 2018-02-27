package ru.ifmo.escience.compbiomed.sandbox.data;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class Event {

    @CsvBindByName(column = "datetime", required = true)
    @CsvDate("HH:mm")
    private Date datetime;

    @CsvBindByName(column = "floor", required = true)
    private int floor;

    @CsvBindByName(column = "door", required = true)
    private int door;

    @CsvBindByName(column = "name", required = true)
    private String name;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
