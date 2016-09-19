package com.mypackage.lab8.store.util.serializers;

import com.mypackage.lab8.store.entity.StoreProxy;
import com.mypackage.lab8.store.model.Store;
import com.mypackage.lab8.store.util.ModelCreator;

import java.io.*;

/**
 * Class for binary serialization
 */
public class BinarySerializer implements Serializer {
    /**
     * Serialize serializable model to binary file
     *
     * @param store    to be serialized
     * @param fileName serialized binary file
     */
    @Override
    public void serialize(Store store, String fileName) {
        StoreProxy storeProxy = ModelCreator.create(store);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(fileName)))) {
            objectOutputStream.writeObject(storeProxy);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new RuntimeException("Error occurred while serializing a file: " + fileName);
        }
    }

    /**
     * Deserialize model from file
     *
     * @param fileName serialized binary file
     * @return serializable set of artists
     */
    @Override
    public StoreProxy deserialize(String fileName) {
        StoreProxy storeProxy;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(fileName)))) {
            storeProxy = (StoreProxy) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Error occurred while reading a file: " + fileName);
        }
        return storeProxy;
    }
}
