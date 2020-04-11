package model;

import java.io.*;

public class Dog implements Cloneable{
    private String name;
    private Cat cat;

    public Dog clone() throws CloneNotSupportedException {
        Dog dog = (Dog) super.clone();
        dog.cat = cat.clone();
        return dog;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", cat=" + cat +
                '}' +
                " Dog's hashcode is " + this.hashCode();
    }

    public Dog() {
    }

    public Dog(String name, Cat cat) {
        this.name = name;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Object deepClone2() throws IOException {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis= null;
        ObjectInputStream ois= null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            oos.writeObject(this);
            Dog dog = (Dog) ois.readObject();

            return dog;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bos.close();
            oos.close();
            bis.close();
            ois.close();
        }


        return null;
    }
}
