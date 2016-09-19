package com.mypackage.lab9_10.dao;


import com.mypackage.lab8.store.model.Artist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.*;

public class XMLArtistDAOTest {
    private IArtistDAO artistDAO;

    @BeforeClass
    public void init() {
        artistDAO = new XMLArtistDAO("src/test/java/com/mypackage/lab9_10/res/data.xml");
    }

    @Test
    public void getById() {
        int id = 2435;
        assertEquals(artistDAO.getById(id).getId(), id);
    }

    @Test
    public void addArtist() {
        assertEquals(artistDAO.addArtist(new Artist(1, "we")), 1);
    }

    @Test
    public void updateArtist() {
        int id = 1;
        String newName = "newName";
        assertTrue(artistDAO.updateArtist(id, newName));
    }

    @Test
    public void removeArtist() {
        int id = 11;
        artistDAO.addArtist(new Artist(id, "artistToBeRemoved"));
        assertTrue(artistDAO.removeArtist(id));
    }

    @Test
    public void getAllArtists() {
        assertNotNull(artistDAO.getAllArtists());
    }


}