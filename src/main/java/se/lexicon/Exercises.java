package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import static se.lexicon.model.Gender.*;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isFirstName = (person) -> person.getFirstName().equalsIgnoreCase("Erik");
        System.out.println(storage.findMany(isFirstName));
        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isFemale = (person) -> person.getGender().equals(FEMALE);
        System.out.println(storage.findMany(isFemale));
        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message){
        System.out.println(message);
        Predicate<Person> isBornAfter = (person) -> person.getBirthDate().isEqual(LocalDate.parse("2000-01-01")) ||
                person.getBirthDate().isAfter(LocalDate.parse("2000-01-01"));

        System.out.println(storage.findMany(isBornAfter));
        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isId = (person) -> person.getId() == 123;
        System.out.println(storage.findOne(isId));
        System.out.println("----------------------");

    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isId = person -> person.getId() == 456;
        Function<Person, String>  personStringFunction = person -> "Name: " + person.getFirstName() + " " + person.getLastName() + "born " + person.getBirthDate().toString();
        System.out.println(storage.findOneAndMapToString(isId,personStringFunction));
        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isStartWithE = person -> person.getFirstName().startsWith("E");
        Function<Person, String> personStringFunction = Person::toString;
        System.out.println(storage.findManyAndMapEachToString(isStartWithE,personStringFunction));

        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isBelow10 = person -> LocalDate.now().getYear() - person.getBirthDate().getYear() < 10;
        Function<Person, String> personStringFunction = person -> person.getFirstName() + " " + person.getLastName() + " "
                + (LocalDate.now().getYear() - person.getBirthDate().getYear()) + " years";
        System.out.println(storage.findManyAndMapEachToString(isBelow10,personStringFunction));
        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isFirstName = person -> person.getFirstName().equalsIgnoreCase("Ulf");
        Consumer<Person> print = person -> System.out.println(person.toString());
        storage.findAndDo(isFirstName,print);
        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isContain = person -> person.getLastName().contains(person.getFirstName());
        Consumer<Person> print = person -> System.out.println(person.toString());
        storage.findAndDo(isContain,print);

        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isFirstnamePalindrome = person -> person.getFirstName().equalsIgnoreCase(new StringBuffer(person.getFirstName()).reverse().toString());
        Consumer<Person> print = person -> System.out.println(person.toString());
        storage.findAndDo(isFirstnamePalindrome,print);

        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isStartWithA = person -> person.getFirstName().startsWith("A");
        Comparator<Person> birthdateCompare = Comparator.comparing(Person::getBirthDate);
        storage.findAndSort(isStartWithA,birthdateCompare).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> isBornBefore = person -> person.getBirthDate().getYear() < 1950;
        Comparator<Person> comparator = Comparator.comparing(Person::getBirthDate).reversed();
        storage.findAndSort(isBornBefore,comparator).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message){
        System.out.println(message);
        //Write your code here
        Comparator<Person> comparator = Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate);
        storage.findAndSort(comparator).forEach(System.out::println);
        System.out.println("----------------------");
    }
}
