package Thread;

public class ThreadDemo1 extends Thread{
    public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        ThreadDemo1 threadDemo2 = new ThreadDemo1();
        threadDemo1.start();
        threadDemo2.start();
    }
}
