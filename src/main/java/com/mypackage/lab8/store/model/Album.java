package com.mypackage.lab8.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class Album {
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "genre")
    private Genre genre;
    @XmlElement(name = "song")
    private Set<Song> songSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Song> getSongSet() {
        return songSet;
    }

    public void setSongSet(Set<Song> songSet) {
        this.songSet = songSet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = Genre.valueOf(genre);
    }

    public void addSong(Song song) {
        songSet.add(song);
    }

    public Album(String title, Genre genre, Set<Song> songSet) {
        this.title = title;
        this.songSet = songSet;
        this.genre = genre;
    }
    public Album(int id,String title, Genre genre, Set<Song> songSet) {
        this.id=id;
        this.title = title;
        this.songSet = songSet;
        this.genre = genre;
    }

    public Album(String title) {
        this.title = title;
        genre = Genre.NO_GENRE;
        songSet = new HashSet<>();
    }

    public Album() {

    }

    @Override
    public String toString() {
        StringBuilder songs = new StringBuilder("\tAlbum: " + title + "; " + genre.toString() + "\n");
        if (songSet.size() > 0) {
            for (Song song : songSet) {
                songs.append(song.toString()).append("\n");
            }
        }
        return songs.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(title, album.title) &&
                Objects.equals(songSet, album.songSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, songSet);
    }
}
