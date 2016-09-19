package com.mypackage.lab8.store.entity;

import com.mypackage.lab8.store.model.Album;
import com.mypackage.lab8.store.model.Genre;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Serializable album class
 */
public class AlbumProxy implements Serializable {
    private String title;
    private Genre genre;
    private Set<SongProxy> songSet;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setSongSet(Set<SongProxy> songSet) {
        this.songSet = songSet;
    }

    public Set<SongProxy> getSongSet() {
        return songSet;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre.toString();
    }

    public AlbumProxy(){
        songSet=new HashSet<>();
    }
    public AlbumProxy(Album album) {
        songSet = new HashSet<>();
        this.genre = album.getGenre();
        this.title = album.getTitle();
        songSet.addAll(album.getSongSet().stream().map(SongProxy::new).collect(Collectors.toList()));
    }

    public String toString() {
        StringBuilder songs = new StringBuilder("\tAlbum: " + title + "; " + getGenre() + "\n");
        for (SongProxy song : songSet) {
            songs.append(song.toString()).append("\n");
        }
        return songs.toString();
    }

    public void addSong(SongProxy song) {
        songSet.add(song);
    }
}
