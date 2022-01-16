import java.util.concurrent.Callable;

public class Adapter {
    public static void main(String[] args) {
        Callable<Long> callable = new Task(123450000L);
        // Thread 类接受一个 Runnable 接口对象，此处用适配器实现了
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
    }
}

// 一个实现了 Callable 接口的类
class Task implements Callable<Long> {
    private long num;
    public Task(long num) {
        this.num = num;
    }

    public Long call() throws Exception {
        long r = 0;
        for (long n = 1; n <= this.num; n++) {
            r = r + n;
        }
        System.out.println("Result: " + r);
        return r;
    }
}

class RunnableAdapter implements Runnable {
    // 引用待转换接口：
    private Callable<?> callable;

    public RunnableAdapter(Callable<?> callable) {
        this.callable = callable;
    }

    // 实现指定接口
    public void run() {
        // 将指定接口调用委托给转换接口调用：
        try {
            callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}