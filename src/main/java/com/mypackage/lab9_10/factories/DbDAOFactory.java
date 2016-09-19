package com.mypackage.lab9_10.factories;

import com.mypackage.lab9_10.dao.DBArtistDAO;
import com.mypackage.lab9_10.dao.IArtistDAO;

public class DbDAOFactory extends DAOFactory {

    /**
     * Get required instance realization
     *
     * @return required IArtistDAO implementation
     */
    @Override
    public IArtistDAO getArtistDAO() {
        return new DBArtistDAO();
    }
}
