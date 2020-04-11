package reflect;

import model.Cat;
import model.Dog;

public class CloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Cat cat = new Cat("cat",1);
        Dog dog1 = new Dog("dog1", cat);
        Dog dog2 = new Dog("dog2", cat);

        System.out.println(cat.toString());
        System.out.println(dog1.toString());
        System.out.println(dog2.toString());

        //深拷贝1
        Cat cat2 = new Cat("cat2",2);
        Dog dog3 = new Dog("dog3",cat2);
        Dog dog4 = dog3.clone();
        System.out.println(dog3.toString());
        System.out.println(dog4.toString());

        //深拷贝2
        Dog dog5 = new Dog("dog5",cat2);
        Dog dog6 = dog5.clone();
        System.out.println(dog5.toString());
        System.out.println(dog6.toString());
    }
}
