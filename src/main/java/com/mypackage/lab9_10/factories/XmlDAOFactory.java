package com.mypackage.lab9_10.factories;

import com.mypackage.lab9_10.dao.IArtistDAO;
import com.mypackage.lab9_10.dao.XMLArtistDAO;

public class XmlDAOFactory extends DAOFactory {
    /**
     * Path to xml file, that stores artists info
     */
    private static final String fileName = "src/main/java/com/mypackage/lab9_10/res/data.xml";

    /**
     * Get path to xml file
     *
     * @return path to xml file
     */
    public static String getFileName() {
        return fileName;
    }

    /**
     * Get required instance realization
     *
     * @return required IArtistDAO implementation
     */
    @Override
    public IArtistDAO getArtistDAO() {
        return new XMLArtistDAO(fileName);
    }
}
