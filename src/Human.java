import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Human {
    String name;
    Human mother;
    Human father;
    int age;

    public Human(String name, Human mother, Human father, int age) {
        this.name = name;
        this.mother = mother;
        this.father = father;
        this.age = age;
    }

    public static void main(String[] args) {
        Human anna = new Human("Anna", null, null, 30);
        Human peter = new Human("Peter", null, null, 28);
        Human john = new Human("John", null, null, 50);
        Human maria = new Human("Maria", null, null, 45);



        List<Human> people = new ArrayList<>();
        people.add(anna);
        people.add(peter);
        people.add(john);
        people.add(maria);


        maria.mother = maria;
        john.father = john;

// 1) Находит ВСЕХ родителей ВСЕХ людей в списке

        List<Human> allParents = people.stream()
                .filter(person -> person.mother != null && person.father != null)
                .map(person -> {
                    List<Human> parents = new ArrayList<>();
                    parents.add(person.mother);
                    parents.add(person.father);
                    return parents;
                })
                .flatMap(List::stream)
                .toList();

        System.out.println("Все родители: " + allParents);



// 2) Находит имена матерей тех людей, которые старше, чем 25 лет

        List<String> mothersOfOlderThan25 = people.stream()
                .filter(person -> person.age > 25 && person.mother != null)
                .map(person -> person.mother.name)
                .toList();

        System.out.println("Имена матерей, которые старше, чем 25 лет: " + mothersOfOlderThan25);
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;

        if (age != human.age) return false;
        if (!Objects.equals(name, human.name)) return false;
        if (!Objects.equals(mother, human.mother)) return false;
        return Objects.equals(father, human.father);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mother != null ? mother.hashCode() : 0);
        result = 31 * result + (father != null ? father.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}