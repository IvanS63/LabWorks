package com.mypackage.lab8.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Artist class
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Artist {
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "album")
    private Set<Album> albumSet;

    public Set<Album> getAlbumSet() {
        return albumSet;
    }

    public void setAlbumSet(Set<Album> albumSet) {
        this.albumSet = albumSet;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist(int id, String name, Set<Album> albumSet) {
        this.id = id;
        this.name = name;
        this.albumSet = albumSet;
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
        albumSet = new HashSet<>();
    }

    public Artist() {

    }

    @Override
    public String toString() {
        StringBuilder songs = new StringBuilder("Artist: " + name + "\n");
        if (albumSet!=null) {
            for (Album album : albumSet) {
                songs.append(album.toString()).append("\n");
            }
        }
        return songs.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) &&
                Objects.equals(albumSet, artist.albumSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, albumSet);
    }
}
