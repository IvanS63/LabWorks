package com.mypackage.lab9_10;

import com.mypackage.lab8.store.model.Artist;
import com.mypackage.lab9_10.dao.IArtistDAO;
import com.mypackage.lab9_10.factories.DAOFactory;
import java.util.Set;

public class Test {

    public void test() {
        DAOFactory factory = DAOFactory.getDAOFactory("XML");
        IArtistDAO artistDAO = factory.getArtistDAO();
        Set<Artist> artistSet=artistDAO.getAllArtists();

        factory=DAOFactory.getDAOFactory("DB");
        artistDAO=factory.getArtistDAO();
        for (Artist artist:artistSet){
            artistDAO.addArtist(artist);
        }
    }
}
