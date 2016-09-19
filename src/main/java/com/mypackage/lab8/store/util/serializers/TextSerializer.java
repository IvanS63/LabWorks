package com.mypackage.lab8.store.util.serializers;

import com.mypackage.lab8.store.entity.AlbumProxy;
import com.mypackage.lab8.store.entity.ArtistProxy;
import com.mypackage.lab8.store.entity.SongProxy;
import com.mypackage.lab8.store.entity.StoreProxy;
import com.mypackage.lab8.store.model.*;
import com.mypackage.lab8.store.util.ModelCreator;

import java.io.*;
import java.util.*;

/**
 * Class for serialization to text file
 */
public class TextSerializer implements Serializer {
    private static final String ELEMENTS_SEPARATOR = ": ";
    private static final String ELEMENT_PARAM_SEPARATOR = "; ";
    private static final String ARTIST_TAG = "Artist";
    private static final String ALBUM_TAG = "Album";
    private static final String SONG_TAG = "Song";

    /**
     * Serialize serializable artist set
     *
     * @param store    to be serialized
     * @param fileName serialized text file
     */
    @Override
    public void serialize(Store store, String fileName) {
        StoreProxy storeProxy = ModelCreator.create(store);
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(fileName)))) {
            for (ArtistProxy artist : storeProxy.getArtistProxySet()) {
                writer.println(ARTIST_TAG + ELEMENTS_SEPARATOR + artist.getId() + ELEMENT_PARAM_SEPARATOR +
                        artist.getName()
                );
                for (AlbumProxy album : artist.getAlbumSet()) {
                    writer.println("\t" + ALBUM_TAG + ELEMENTS_SEPARATOR
                            + album.getTitle() + ELEMENT_PARAM_SEPARATOR + album.getGenre());
                    for (SongProxy song : album.getSongSet()) {
                        writer.println("\t\t" + SONG_TAG + ELEMENTS_SEPARATOR + song.getTitle() +
                                ELEMENT_PARAM_SEPARATOR +
                                song.getDuration());
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error occurred while writing a file: " + fileName);
        }

    }

    /**
     * Deserialize artist set from text file
     *
     * @param fileName serialized text file
     * @return serializable store
     */
    @Override
    public StoreProxy deserialize(String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File has not been found: " + fileName);
        }
        LinkedList<ArtistProxy> artistList = new LinkedList<>();
        LinkedList<AlbumProxy> albumList = new LinkedList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] elements = line.trim().split(ELEMENTS_SEPARATOR);
            switch (elements[0]) {
                case ARTIST_TAG:
                    String artistParams[] = elements[1].split(ELEMENT_PARAM_SEPARATOR);
                    Artist artist = new Artist(Integer.parseInt(artistParams[0].trim()), artistParams[0].trim());
                    artistList.add(new ArtistProxy(artist));
                    break;
                case ALBUM_TAG:
                    String albumParams[] = elements[1].split(ELEMENT_PARAM_SEPARATOR);
                    Album album = new Album(albumParams[0].trim());
                    album.setGenre(albumParams[1].trim());
                    albumList.add(new AlbumProxy(album));
                    artistList.getLast().addAlbum(albumList.getLast());
                    break;
                case SONG_TAG:
                    String songParams[] = elements[1].split(ELEMENT_PARAM_SEPARATOR);
                    albumList.getLast().addSong(new SongProxy(
                            new Song(songParams[0], Integer.parseInt(songParams[1].trim()))));
                    break;
                default:
                    throw new RuntimeException("Invalid file structure: " + fileName);
            }
        }
        StoreProxy storeProxy = new StoreProxy();
        storeProxy.setArtistProxySet(new HashSet<>(artistList));
        return storeProxy;
    }
}
