package com.mypackage.lab8;

import com.mypackage.lab8.store.entity.StoreProxy;
import com.mypackage.lab8.store.model.Store;
import com.mypackage.lab8.store.util.DataValidator;
import com.mypackage.lab8.store.util.Filler;
import com.mypackage.lab8.store.util.serializers.BinarySerializer;
import com.mypackage.lab8.store.util.serializers.Serializer;
import com.mypackage.lab8.store.util.serializers.TextSerializer;

/**
 * Testing serialization and deserialization
 */
public class Test {
    /**
     * Path to serialized file
     */
    private static final String BINARY_FILE_NAME = "src/main/java/com/mypackage/lab8/res/data.ser";
    private static final String TEXT_FILE_NAME = "src/main/java/com/mypackage/lab8/res/data.txt";

    /**
     * Test serializers
     *
     * @param store      Set to be serialized
     * @param serializer Serializer instance
     * @param fileName   file to store serialized model
     */
    private static void testSerializer(Store store, Serializer serializer, String fileName) {
        serializer.serialize(store, fileName);
        System.out.println("Serializing to..." + fileName);
        System.out.println("Completed");
        System.out.println("Deserializing from file..." + fileName);
        StoreProxy storeProxy = serializer.deserialize(fileName);
        System.out.println(storeProxy.toString());
    }

    public static void test() {
        //Filling and validating data before serializing
        Store store = new Store(DataValidator.validateArtists(Filler.fillData()));
        //Testing serializers
        testSerializer(store, new TextSerializer(), TEXT_FILE_NAME);
        testSerializer(store, new BinarySerializer(), BINARY_FILE_NAME);

    }
}
