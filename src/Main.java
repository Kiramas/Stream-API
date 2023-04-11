import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    Collection<Person> persons = new ArrayList<>();
    for(
    int i = 0;
    i< 10_000_000;i++)

    {
        persons.add(new Person(
                names.get(new Random().nextInt(names.size())),
                families.get(new Random().nextInt(families.size())),
                new Random().nextInt(100),
                Sex.values()[new Random().nextInt(Sex.values().length)],
                Education.values()[new Random().nextInt(Education.values().length)])
        );
    }

    long minors = persons.stream()
            .filter(person -> person.getAge() < 18)
            .count();
        System.out.println("Несовершеннолетние: " + minors);

    List<String> recruits = persons.stream()
            .filter(person -> person.getAge() >= 18)
            .filter(person -> person.getAge() < 27)
            .filter(person -> person.getSex() == Sex.MAN)
            .map(Person::getFamily)
            .collect(Collectors.toList());
        System.out.println("Рекруты: " + recruits.size());

    List<Person> workingAge = persons.stream()
            .filter(a -> a.getAge() >= 18)
            .filter(e -> e.getEducation() == Education.HIGHER)
            .filter(p -> p.getSex() == Sex.WOMAN && p.getAge() < 60 || p.getSex() == Sex.MAN && p.getAge() < 65)
            .sorted(Comparator.comparing(Person::getFamily))
            .collect(Collectors.toList());
        System.out.println("Работающие: " + workingAge.size());

}}