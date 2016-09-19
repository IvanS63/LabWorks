package com.mypackage.lab9_10.dao;

import com.mypackage.lab8.store.model.Artist;

import java.util.Set;

public interface IArtistDAO {
    Artist getById(int id);

    int addArtist(Artist artist);

    boolean updateArtist(int id, String name);

    boolean removeArtist(int id);

    Set<Artist> getAllArtists();

}
