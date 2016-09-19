package com.mypackage.lab9_10.parsers;

import com.mypackage.lab8.store.model.Store;

/**
 * Store parser interface
 */
public interface StoreParser {
    /**
     * Save model to file
     *
     * @param store    model to be saved
     * @param fileName file
     */
    void saveStore(Store store, String fileName);

    /**
     * Get model from file
     *
     * @param fileName file
     * @return Store model instance
     */
    Store loadStore(String fileName);
}
