package com.jylu.study.cuncurrent;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lujiayun
 * Date: 2017-11-11
 * Time: 22:30
 */
public class FutureTaskPractice {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<String> future = service.submit(new CallTask());
        FutureTask<String> futureTask = new FutureTask<>(new CallTask());
        service.submit(futureTask);
        String futureResult = "";
        String futureTaskResult = "";
        try {
            futureTaskResult = futureTask.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.err.println("获取结果超时");
        }
        try {
            futureResult = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!futureTaskResult.equals("")){
            System.out.println(futureTaskResult);
        }
        if(!futureResult.equals("")){
            System.out.println(futureResult);
        }
        service.shutdown();
    }

}

class CallTask implements Callable<String>{

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "Call 结果返回...";
    }
}
