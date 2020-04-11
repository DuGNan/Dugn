package reflect;

import model.Person;
import model.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo1 {
    public static void main(String[] args) {
        Person person1 = new Student();
        Class clazz = person1.getClass();
        Method[] method=clazz.getDeclaredMethods();
        for (Method m : method) {
            System.out.println("方法:" + m.toString());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("属性:" + f.toString());
        }
    }

}
