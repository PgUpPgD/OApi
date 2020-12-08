package com.zh.oapi_resource.util;

import java.util.concurrent.*;

/**
 * 可取消的异步任务
 */    //https://www.jianshu.com/p/55221d045f39 地址

//1:封装一个计算任务，实现Callable接口
public class Task implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        try{
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + "...i = " + i);
                //模拟耗时操作
                Thread.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("计算中断将停止");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //1.FutureTask + Thread
        //创建计算任务，作为参数传入FutureTask
        Task task = new Task();
        FutureTask futureTask = new FutureTask(task);
        //将futureTask提交给Thread执行
        Thread thread = new Thread(futureTask);
        thread.setName("task thread 1");
        thread.start();

        //获取执行结果，由于get()方法可能会阻塞当前调用线程
        //如果子任务执行时间不确定，最好在子线程中获取执行结果
        try {
            // boolean result = (boolean) futureTask.get();   //设置超时时间
            boolean result = (boolean) futureTask.get(5, TimeUnit.SECONDS);
            System.out.println("result:" + result);
        } catch (InterruptedException e) {
            System.out.println("守护线程阻塞被打断");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("执行任务时出错");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("执行超时");
            //用来结束线程
            futureTask.cancel(true);
            e.printStackTrace();
        } catch (CancellationException e) {
            //如果线程已经cancel了，再执行get操作会抛出这个异常
            System.out.println("future已经cancel了");
            e.printStackTrace();
        }
    }


    //2.Future + ExecutorService
    //step1 ......
    //step2:创建计算任务
    // Task task = new Task();
    //step3:创建线程池，将Callable类型的task提交给线程池执行，通过Future获取子任务的执行结果
    // ExecutorService executorService = Executors.newCachedThreadPool();
    // final Future<Boolean> future = executorService.submit(task);
    //step4：通过future获取执行结果
    //boolean result = (boolean) future.get();

    //3.FutureTask + ExecutorService
    //step1 ......
    //step2 ......
    //step3:将FutureTask提交给线程池执行
    //ExecutorService executorService = Executors.newCachedThreadPool();
    //executorService.execute(futureTask);
    //step4 ......

}
