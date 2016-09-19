package com.mypackage.lab9_10.factories;

import com.mypackage.lab9_10.dao.IArtistDAO;

/**
 * Abstract factory class
 */
public abstract class DAOFactory {
    /**
     * Get required instance realization
     *
     * @return required IArtistDAO implementation
     */
    public abstract IArtistDAO getArtistDAO();

    /**
     * Get instance of factory
     *
     * @param source used to select required factory
     * @return factory instance depending on param
     */
    public static DAOFactory getDAOFactory(String source) {
        switch (source) {
            case "XML":
                return new XmlDAOFactory();
            case "DB":
                return new DbDAOFactory();
            default:
                return null;
        }
    }

}
