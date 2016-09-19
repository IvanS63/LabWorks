package com.mypackage.lab8.store.util;

import com.mypackage.lab8.store.model.Album;
import com.mypackage.lab8.store.model.Artist;
import com.mypackage.lab8.store.model.Genre;
import com.mypackage.lab8.store.model.Song;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for filling data for music store
 */
public class Filler {
    /**
     * Fill data: songs, albums, artists
     */
    public static Set<Artist> fillData() {
        Set<Artist> artistSet = new HashSet<>();
        Set<Song> songsACDCFirstAlbum = new HashSet<>();
        songsACDCFirstAlbum.add(new Song(1, "Highway to Hell", 328));
        songsACDCFirstAlbum.add(new Song(2, "Touch Too Much", 420));
        songsACDCFirstAlbum.add(new Song(3, "Get It Hot", 511));
        songsACDCFirstAlbum.add(new Song(4, "Shot Down in Flames", 345));

        Set<Song> songsACDCSecondAlbum = new HashSet<>();
        songsACDCSecondAlbum.add(new Song(5, "Shoot to Thrill", 517));
        songsACDCSecondAlbum.add(new Song(6, "Back in Black", 343));
        songsACDCSecondAlbum.add(new Song(7, "You Shook Me All Night Long", 521));
        songsACDCSecondAlbum.add(new Song(8, "Have a Drink on Me", 256));

        Set<Song> songsRammFirstAlbum = new HashSet<>();
        songsRammFirstAlbum.add(new Song(9, "Reise, Reise", 332));
        songsRammFirstAlbum.add(new Song(10, "Keine Lust", 321));
        songsRammFirstAlbum.add(new Song(11, "Amerika", 501));
        songsRammFirstAlbum.add(new Song(12, "Los", 404));

        Set<Album> acdcAlbums = new HashSet<>();
        acdcAlbums.add(new Album(1, "Highway to Hell", Genre.ROCK, songsACDCFirstAlbum));
        acdcAlbums.add(new Album(2, "Back in Black", Genre.ROCK, songsACDCSecondAlbum));

        Set<Album> rammAlbums = new HashSet<>();
        rammAlbums.add(new Album(3, "Sonne", Genre.INDUSTRIAL_METAL, songsRammFirstAlbum));

        artistSet.add(new Artist(12345, "AC/DC", acdcAlbums));
        artistSet.add(new Artist(2435, "Rammstein", rammAlbums));
        artistSet.add(new Artist(3, "NoNAME"));


        return artistSet;
    }
}
