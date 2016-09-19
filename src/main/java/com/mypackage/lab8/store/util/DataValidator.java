package com.mypackage.lab8.store.util;

import com.mypackage.lab8.store.model.Album;
import com.mypackage.lab8.store.model.Artist;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for validating music store data
 */
public class DataValidator {
    /**
     * Validate artists albums having empty albums and no songs
     *
     * @param artistSet set to be validated
     * @return artist set without artists with empty albums
     */
    public static Set<Artist> validateArtists(Set<Artist> artistSet) {
        artistSet = artistSet.stream().filter(a -> !a.getAlbumSet().isEmpty()).collect(Collectors.toCollection
                (HashSet<Artist>::new));
        for (Artist artist : artistSet) {
            artist.setAlbumSet(validateAlbums(artist.getAlbumSet()));
        }
        return artistSet;
    }

    /**
     * Validate albums having no songs
     *
     * @param albumSet albums set to be validated
     * @return albums set without empty albums
     */
    private static Set<Album> validateAlbums(Set<Album> albumSet) {
        return albumSet.stream().filter(a -> !a.getSongSet().isEmpty()).collect(Collectors.toCollection
                (HashSet<Album>::new));
    }
}
