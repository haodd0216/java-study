// City
public class Main {
    public static void main(String[] args) {
        City bj = new City();
        bj.name = "Beijing";
        bj.latitude = 39.903;
        bj.longitude = 116.401;
        System.out.println(bj.name);
        System.out.println("location: " + bj.latitude + ", " + bj.longitude);

        Person person = new Person();
        person.setName("小红");
        person.setAge(33);
        System.out.println("我叫" + person.getName() + "，今年" + person.getAge() + "岁");
    }
}

class City {
    public String name;
    public double latitude;
    public double longitude;
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if(age <= 0 || age> 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }
}