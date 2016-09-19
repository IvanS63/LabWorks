package com.mypackage.lab9_10.dao;

import com.mypackage.lab8.store.model.Album;
import com.mypackage.lab8.store.model.Artist;
import com.mypackage.lab8.store.model.Song;
import com.mypackage.lab8.store.model.Store;
import com.mypackage.lab9_10.parsers.JaxbStoreParser;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class XMLArtistDAO implements IArtistDAO {
    private String xmlFileName;
    private Store store;
    private static JaxbStoreParser jaxbStoreParser;
    private static final Logger logger = Logger.getLogger(XMLArtistDAO.class);

    private Store getStore() {
        return jaxbStoreParser.loadStore(xmlFileName);
    }

    @Override
    public Artist getById(int id) {
        try {
            return getStore().getArtistSet().stream().
                    filter(a -> a.getId() == id).findFirst().get();

        } catch (NoSuchElementException ex) {
            logger.error("NO ARTIST WITH id=" + id + " exists");
            System.exit(1);
            return null;
        }
    }

    @Override
    public int addArtist(Artist artist) {
        store = getStore();
        store.getArtistSet().add(artist);
        jaxbStoreParser.saveStore(store, xmlFileName);
        logger.info("ADDED artist: id=" + artist.getId() + " name: " + artist.getName());
        return artist.getId();
    }

    @Override
    public boolean updateArtist(int id, String name) {
        store.getArtistSet().stream().filter(a -> a.getId() == id).findAny().
                map(a -> {
                    a.setName(name);
                    return true;
                });
        jaxbStoreParser.saveStore(store, xmlFileName);
        logger.info("UPDATED artist: id=" + id);
        return getById(id).getName().equals(name);
    }

    @Override
    public boolean removeArtist(int id) {
        boolean result = store.getArtistSet().remove(getById(id));
        jaxbStoreParser.saveStore(store, xmlFileName);
        logger.info("REMOVED artist: id=" + id);
        return result;
    }

    @Override
    public Set<Artist> getAllArtists() {
        return new HashSet<>(getStore().getArtistSet());
    }

    public double getAverageSongsTime(int id) {
        double averageSongTime = 0;
        int songsCount = 0;
        Artist artist = getById(id);
        if (artist.getAlbumSet() == null) {
            logger.warn("ARTIST id: " + id + " has no albums");
            return 0;
        }
        for (Album album : getById(id).getAlbumSet()) {
            if (album.getSongSet() == null) {
                logger.warn("ARTIST id: " + id + " has no songs in album " + album.getTitle());
                return 0;
            }
            for (Song song : album.getSongSet()) {
                averageSongTime += song.getDuration();
                songsCount++;
            }
        }
        logger.info("AVERAGE song duration for id=" + id + " is " + averageSongTime / songsCount + " sec");
        return averageSongTime / songsCount;
    }

    public XMLArtistDAO(String fileName) {
        jaxbStoreParser = new JaxbStoreParser();
        xmlFileName = fileName;
    }
}
