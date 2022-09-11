package task2.task21;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeMap;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Task21 {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Task21.Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Task21.Person[] RAW_DATA = new Task21.Person[]{

            new Task21.Person(0, "Harry"),
            new Task21.Person(0, "Harry"), // дубликат
            new Task21.Person(1, "Harry"), // тёзка
            new Task21.Person(2, "Harry"),
            new Task21.Person(3, "Emily"),
            new Task21.Person(4, "Jack"),
            new Task21.Person(4, "Jack"),
            new Task21.Person(5, "Amelia"),
            new Task21.Person(5, "Amelia"),
            new Task21.Person(6, "Amelia"),
            new Task21.Person(7, "Amelia"),
            new Task21.Person(8, "Amelia"),
            new Task21.Person(0, "Amelia"),
            new Task21.Person(0, "Amelia"),
            new Task21.Person(14, null),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        if (RAW_DATA == null) {
            System.out.println("Отсутствует входной массив данных");
            System.exit(-1);
        }

        for (Task21.Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        TreeMap<String, Long> SUMMARY_DATA = generateSummary(RAW_DATA);

        SUMMARY_DATA.entrySet()
                .forEach(entry -> System.out.println("Key: " + entry.getKey()
                        + System.lineSeparator()
                        + "Value:" + entry.getValue()));

        TreeMap<String, Long> TEST_DATA = new TreeMap<>();
        TEST_DATA.put("Amelia", 5L);
        TEST_DATA.put("Emily", 1L);
        TEST_DATA.put("Harry", 3L);
        TEST_DATA.put("Jack", 1L);


        assert TEST_DATA.keySet().equals(SUMMARY_DATA.keySet());
        assert TEST_DATA.values().containsAll(SUMMARY_DATA.values());


        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */

    }

    private static TreeMap<String, Long> generateSummary(Person[] persons) {

        if (persons == null) {
            System.out.println("Отсутствует входной массив данных");
            System.exit(-1);
        }

        return new TreeMap<>(
                Arrays.stream(RAW_DATA)
                        .distinct()
                        .filter(person -> Objects.nonNull(person) && Objects.nonNull(person.getName()))
                        .map(Person::getName)
                        .sorted()
                        .collect(groupingBy(String::intern, counting())));

    }
}