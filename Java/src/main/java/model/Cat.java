package model;

public class Cat implements Cloneable{
    private String name;
    private Integer age;

    public Cat clone() throws CloneNotSupportedException {
        return (Cat) super.clone();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' + " Cat's hashcode is " + this.hashCode();
    }

    public Cat() {
    }

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
