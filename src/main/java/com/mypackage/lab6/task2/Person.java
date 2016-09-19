package com.mypackage.lab6.task2;

import java.util.Random;

/**
 * Class Person with attributes:name, age, sex
 */
public class Person {
    private String name;
    private int age;
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public enum Gender {MALE, FEMALE}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return getName() + " " + getAge() + " " + getGender() + "\n";
    }

    public Person(String name, int age, Gender gender) {
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    public Person() {

    }

    /**
     * Generate person with random attributes
     *
     * @return Person with random name, age and sex
     */
    public static Person generatePerson() {
        Random random = new Random();
        Person person;
        String[] randomMenNames = new String[]{"Martin", "Jon", "James", "George", "Sam"};
        String[] randomWomenNames = new String[]{"Ann", "Kate", "Marie", "Lysa", "Olga"};
        int randomIndex = random.nextInt(randomMenNames.length);
        int age = random.nextInt(50) + 1;
        if (age % 2 == 0) {
            return new Person(randomMenNames[randomIndex], age, Gender.MALE);
        } else {
            return new Person(randomWomenNames[randomIndex], age, Gender.FEMALE);
        }
    }
}
