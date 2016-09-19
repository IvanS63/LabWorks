package com.mypackage.lab9_10.dao;

import com.mypackage.lab8.store.model.Album;
import com.mypackage.lab8.store.model.Artist;
import com.mypackage.lab8.store.model.Song;
import com.mypackage.lab9_10.util.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DBArtistDAO implements IArtistDAO {
    private static final Logger logger = Logger.getLogger(DBArtistDAO.class);


    @Override
    public Artist getById(int id) {
        Artist artist = new Artist();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name FROM artist WHERE " +
                    "id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artist.setId(resultSet.getInt("id"));
                artist.setName(resultSet.getString("name"));
                artist.setAlbumSet(getAlbums(id));
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return artist;
    }

    /**
     * Add artist to DB
     *
     * @param artist artist to be inserted
     * @return inserted artist Id, otherwise -1
     */
    @Override
    public int addArtist(Artist artist) {
        if (artist == null || artist.getAlbumSet().isEmpty()) {
            logger.warn("Attempt to add incorrect artist");
            return -1;
        }
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artist VALUES (?,?)");
            preparedStatement.setInt(1, artist.getId());
            preparedStatement.setString(2, artist.getName());
            preparedStatement.executeUpdate();
            for (Album album : artist.getAlbumSet()) {
                setAlbums(artist.getId(), album);
            }
            logger.info("ADDED: Artist id = " + artist.getId());
            return artist.getId();
        } catch (SQLException ex) {
            logger.error(ex);
            return -1;
        }
    }

    /**
     * Insert into DB album of an artist
     *
     * @param authorId artist, which albums to be added
     * @param album    artist album to be inserted
     */
    private void setAlbums(int authorId, Album album) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO album VALUES (?,?,?,?)");
            preparedStatement.setInt(1, album.getId());
            preparedStatement.setString(2, album.getTitle());
            preparedStatement.setString(3, album.getGenre().toString());
            preparedStatement.setInt(4, authorId);
            preparedStatement.executeUpdate();
            for (Song song : album.getSongSet()) {
                setAlbumSongs(album.getId(), song);
            }
            logger.info("ADDED: Album id = " + album.getId());
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    /**
     * Insert into DB all songs of an album
     *
     * @param albumId album, which songs to be added
     * @param song    song to be inserted
     */
    private void setAlbumSongs(int albumId, Song song) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO song VALUES (?,?,?,?)");
            preparedStatement.setInt(1, song.getId());
            preparedStatement.setString(2, song.getTitle());
            preparedStatement.setInt(3, song.getDuration());
            preparedStatement.setInt(4, albumId);
            preparedStatement.executeUpdate();
            logger.info("ADDED: Song id = " + song.getId());
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    /**
     * Update artist into DB
     *
     * @param id   Id artist to be updated
     * @param name new artist name
     * @return update successful - true, otherwise - false
     */
    @Override
    public boolean updateArtist(int id, String name) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE artist SET name=? WHERE id=?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() >= 1) {
                logger.info("DB: updated artist with id = " + id);
                return true;
            } else {
                logger.warn("DB: no artist has been updated with id =" + id);
                return false;
            }
        } catch (SQLException ex) {
            logger.error(ex);
            return false;
        }
    }

    /**
     * Remove artist from DB
     *
     * @param id Id artist to be removed
     * @return remove successful - true, otherwise - false
     */
    @Override
    public boolean removeArtist(int id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM artist WHERE id=?");
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() >= 1) {
                logger.info("DB: removed artist with id = " + id);
                return true;
            } else {
                logger.warn("DB: no artist has been removed, id = " + id);
                return false;
            }
        } catch (SQLException ex) {
            logger.error(ex);
            return false;
        }
    }

    /**
     * Get all artist From DB
     *
     * @return Set of Artist
     */
    @Override
    public Set<Artist> getAllArtists() {
        Set<Artist> artistSet = new HashSet<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name FROM artist");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt("id"));
                artist.setName(resultSet.getString("name"));
                artist.setAlbumSet(getAlbums(artist.getId()));
                artistSet.add(artist);
            }
            resultSet.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return artistSet;
    }


    /**
     * Get all albums of author
     *
     * @param id author id to get albums
     * @return Album Set
     */
    private Set<Album> getAlbums(int id) {
        Set<Album> albumSet = new HashSet<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,title, genre FROM album " +
                    "WHERE artist_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setId(resultSet.getInt("id"));
                album.setTitle(resultSet.getString("title"));
                album.setGenre(resultSet.getString("genre"));
                album.setSongSet(getAlbumSongs(album.getId()));
                albumSet.add(album);
            }
            resultSet.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return albumSet;
    }

    /**
     * Get songs of an album
     *
     * @param id album Id to get songs
     * @return Set Song
     */
    private Set<Song> getAlbumSongs(int id) {
        Set<Song> songSet = new HashSet<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,title, duration FROM song " +
                    "WHERE album_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Song song = new Song();
                song.setId(resultSet.getInt("id"));
                song.setTitle(resultSet.getString("title"));
                song.setDuration(resultSet.getInt("duration"));
                songSet.add(song);
            }
            resultSet.close();
        } catch (SQLException ex) {
            logger.error(ex);

        }
        return songSet;
    }
}
