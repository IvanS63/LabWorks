package com.mypackage.lab8.store.util;

import com.mypackage.lab8.store.entity.StoreProxy;
import com.mypackage.lab8.store.model.Store;

/**
 * Class for creating serializable model from existing music store java model
 */
public class ModelCreator {
    /**
     * Create Serializable Artist set
     *
     * @param store to be prepared for serialization
     * @return serializable store
     */
    public static StoreProxy create(Store store) {
        return new StoreProxy(store);
    }

}
