package com.mypackage.lab8.store.util.serializers;

import com.mypackage.lab8.store.entity.StoreProxy;
import com.mypackage.lab8.store.model.Store;

/**
 * Interface for serializing music store entity
 */
public interface Serializer {
    /**
     * Serialize entity to file
     *
     * @param store set to be serialized
     * @param fileName  serialized file
     */
    void serialize(Store store, String fileName);

    /**
     * Deserialize entity from file
     *
     * @param fileName serialized file
     * @return storeProxy
     */
    StoreProxy deserialize(String fileName);
}

