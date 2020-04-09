package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("任务开始执行...");

        try {
            System.out.println("执行结果是："+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("任务执行完成...");


    }
}

class Task implements Callable{

    public Object call() throws Exception {
        System.out.println("任务开始计算....");
        int sum = 0;
        for (int i=0;i<5;i++){
            sum = sum + i;
        }
        return sum;
    }
}
