package com.mypackage.lab9_10.parsers;

import com.mypackage.lab8.store.model.Store;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbStoreParser implements StoreParser {
    Logger logger = Logger.getLogger(JaxbStoreParser.class);

    /**
     * Save model to file
     *
     * @param store    model to be saved
     * @param fileName file
     */
    @Override
    public void saveStore(Store store, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Store.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(store, new File(fileName));
        } catch (JAXBException ex) {
            logger.error(ex);
            throw new RuntimeException("Error occurred writing file: " + fileName);
        }
    }

    /**
     * Get model from file
     *
     * @param fileName file
     * @return StoreProxy model instance
     */
    @Override
    public Store loadStore(String fileName) {
        Store store;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Store.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            store = (Store) jaxbUnmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException ex) {
            logger.error(ex);
            throw new RuntimeException("Error reading xml file: " + fileName);
        }
        return store;
    }
}
