import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

       System.out.print("Количество несовершеннолетних: ");
        long count = persons.stream().
                filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

       /* 2___________________Получаем список призывников__________________________________*/
        List<String> recruitList = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        for (String familyOfRecruiter : recruitList) System.out.println(familyOfRecruiter);

       /* 3___________________ПОЛУЧАЕМ СПИСОК РАБОТНИКОВ__________________________________*/
        List<String> workerList = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() < 60)
                        || (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() < 65))
                .map(person -> person.getFamily())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        for (String familyOfWorker : workerList) System.out.println(familyOfWorker);

    }
}
