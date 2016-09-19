package com.mypackage.lab8.store.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Song class
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "duration")
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    public Song(int id,String title, int duration) {
        this.id=id;
        this.title = title;
        this.duration = duration;
    }

    public Song() {
    }

    @Override
    public String toString() {
        return "\t\t" + title + "; " + duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration &&
                Objects.equals(title, song.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration);
    }

}
