package Thread;

public class RunnableDemo implements java.lang.Runnable {
    public void run() {
        System.out.println("RunnableDemo Implements");
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread thread = new Thread(runnableDemo);
        thread.start();
    }
}
