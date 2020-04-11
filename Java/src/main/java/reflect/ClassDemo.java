package reflect;

import model.Person;
import model.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassDemo {
    public static void main(String[] args) {
        Person student1 = new Student();
        Class clazz = student1.getClass();

        //比较getMethods和getDeclaredMethods
        Method[] methods1 = clazz.getMethods();
        for (Method method : methods1) {
            System.out.println("getMethods获得的方法 " + method.toString());
        }

        Method[] methods2 = clazz.getDeclaredMethods();
        for (Method method : methods2) {
            System.out.println("getDeclaredMethods获得的方法 " + method.toString());
        }

        //比较getFields和getDeclaredFields
        Field[] fields1 = clazz.getFields();
        for (Field field : fields1) {
            System.out.println("getFields获得的属性 " + field.toString());
        }

        Field[] fields2 = clazz.getDeclaredFields();
        for (Field field : fields2) {
            System.out.println("getDeclaredFields获得的属性 " + field.toString());
        }
    }
}
