package com.mypackage.lab6.task1;

import com.mypackage.lab6.task1.consumers.*;
import com.mypackage.lab6.task1.util.Injector;

public class Test {
    public static void main(String args[]) {
        Consumer consumer=new ChildConsumer();
        new Injector().inject(consumer);
        consumer.getCache();
    }
}
