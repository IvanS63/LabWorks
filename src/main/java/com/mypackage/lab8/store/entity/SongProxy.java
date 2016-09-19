package com.mypackage.lab8.store.entity;

import com.mypackage.lab8.store.model.Song;

import java.io.Serializable;

/**
 * Serializable song class
 */
public class SongProxy implements Serializable {
    private String title;
    private int duration;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public SongProxy(Song song) {
        this.title = song.getTitle();
        this.duration = song.getDuration();
    }

    public SongProxy() {

    }

    public String toString() {
        return "\t\t" + title + "; " + duration;
    }


}
