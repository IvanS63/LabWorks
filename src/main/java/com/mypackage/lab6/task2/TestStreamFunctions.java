package com.mypackage.lab6.task2;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.*;

/**
 * Class for testing stream functions
 */
public class TestStreamFunctions {
    private List<Person> listOfPeople;
    private final int STREAM_ELEMENTS_COUNT = 20;

    /**
     * Generate List of Person with set size
     */
    private void generateSamples() {
        listOfPeople = Stream.generate(Person::generatePerson).limit(STREAM_ELEMENTS_COUNT).collect(Collectors.toList());
    }

    /**
     * Print all people from stream
     */
    private void printAllPeople() {
        listOfPeople.stream().forEach(System.out::print);
    }

    /**
     * Get average age
     *
     * @return average age from stream
     */
    private double getAverageAge() {
        return listOfPeople.stream().mapToInt(Person::getAge).average().getAsDouble();
    }

    /**
     * Sort stream by age
     */
    private void sortByAge() {
        System.out.println("--------\nTesting sort:");
        listOfPeople.stream().sorted((person1, person2) -> Integer.compare(person1.getAge(), person2.getAge())).forEach(System.out::print);
    }

    /**
     * Group by people names
     */
    private void printPeopleDuplicateNames() {
        System.out.println("--------\nTesting counting different names:");
        Map<String, Long> map =
                listOfPeople.stream().collect(Collectors.groupingBy(p -> p.getName(), Collectors.counting()));
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Set all women age=age-10;
     */
    private void rejuvenateWomen(UnaryOperator<Person> function) {
        System.out.println("--------\nTesting decrementing age function");
        System.out.println("Women with age>10");
        listOfPeople.stream().forEach(System.out::print);
        System.out.println("Rejuvenated women");
        listOfPeople.stream().map(function).forEach(System.out::print);
    }

    /**
     * Filter the stream and print all adults (age>=18)
     */
    private void printAdults() {
        System.out.print("--------\nList of adults:");
        listOfPeople.stream().filter((p) -> p.getAge() >= 18).forEach(System.out::print);
    }

    /**
     * Testing all functions from the task
     */
    public void test() {
        generateSamples();
        System.out.println("Getting random people ");
        printAllPeople();
        sortByAge();
        printPeopleDuplicateNames();
        System.out.println("--------\nAverage age:" + getAverageAge());
        rejuvenateWomen(person -> {
            if (person.getGender() == Person.Gender.FEMALE && person.getAge() > 11) {
                person.setAge(person.getAge() - 10);
            }
            return person;
        });
    }
}
