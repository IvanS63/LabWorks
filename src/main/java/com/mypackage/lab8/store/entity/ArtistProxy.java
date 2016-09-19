package com.mypackage.lab8.store.entity;

import com.mypackage.lab8.store.model.Artist;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Serializable Artist class
 */
public class ArtistProxy implements Serializable {

    private int id;

    private String name;

    private Set<AlbumProxy> albumSet;

    public Set<AlbumProxy> getAlbumSet() {
        return albumSet;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAlbumSet(Set<AlbumProxy> albumSet) {
        this.albumSet = albumSet;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArtistProxy(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        albumSet = new HashSet<>();
        albumSet.addAll(artist.getAlbumSet().stream().map(AlbumProxy::new).collect(Collectors.toList()));
    }

    public ArtistProxy() {
        albumSet = new HashSet<>();
    }

    public String toString() {
        StringBuilder songs = new StringBuilder("Artist: " + name + "\n");
        for (AlbumProxy album : albumSet) {
            songs.append(album.toString()).append("\n");
        }
        return songs.toString();
    }

    public void addAlbum(AlbumProxy album) {
        albumSet.add(album);
    }
}
