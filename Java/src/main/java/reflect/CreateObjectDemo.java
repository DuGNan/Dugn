package reflect;

import model.Person;
import model.Student;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateObjectDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CloneNotSupportedException, IOException {
        //1.使用new关键字
        Student student1 = new Student();
        student1.setName("张三");
        System.out.println("使用new关键字创建的实例:" + student1.getName() + " hascode: " + student1.hashCode());

        //2.使用newInstance()
        Student student2 = (Student) Class.forName("model.Student").newInstance();
        student2.setName("张三");
        System.out.println("使用newInstance创建的实例:" + student2.getName() + " hascode: " + student2.hashCode());

        //3.使用Constructor类的newInstance方法
        Constructor<Student> constructor = (Constructor<Student>) Class.forName("model.Student").getConstructor();
        Student student3 = constructor.newInstance();
        student3.setName("张三");
        System.out.println("使用constructor创建的实例:" + student3.getName() + " hascode: " + student3.hashCode());

        //4.使用clone
        Student student4 = student1.clone();
        System.out.println("使用clone创建的实例:" + student4.getName() + " hascode: " + student4.hashCode());

        //5.使用反序列化创建对象
        //5.1:将对象序列化
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
        out.writeObject(student1);
        out.close();
        //5.2:对象反序列化
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
        Student student5 = (Student) in.readObject();
        in.close();
        student5.setName("张三的序列化对象");
        System.out.println("使用反序列创建的实例:" + student5.getName() + " hascode: " + student5.hashCode());
    }
}
