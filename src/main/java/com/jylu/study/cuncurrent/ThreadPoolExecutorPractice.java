package com.jylu.study.cuncurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lujiayun
 * Date: 2017-11-11
 * Time: 22:44
 */
public class ThreadPoolExecutorPractice {

    public static void main(String[] args) {
        CustomThreadPoolExecutor custom = new CustomThreadPoolExecutor();
        ThreadPoolExecutor service = custom.getBlockThreadPoolExecutor();
        // 当空闲时间达到keepAliveTime时,coreSize也将关闭
        service.allowCoreThreadTimeOut(true);
        // 创建30个线程来执行任务,缓冲队列中有10个任务,提交第41的时候就会拒绝
        // 因为execute中提交到任务队列是用的offer方法:workQueue.offer(command),这个方法是非阻塞的,因此会马上拒绝任务
        // 所以当有大量的任务来时,如果不设置阻塞队列的容量,会OOM;如果设置阻塞队列的容量则会有任务不会被处理
        for(int i = 0; i < 41; i++){
            service.execute(new ThreadPoolTask());
        }
        custom.shutdown();
    }

}

class ThreadPoolTask implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is running.....");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class CustomThreadPoolExecutor{

    private ThreadPoolExecutor threadPoolExecutor = null;

    /**
     * 初始化线程池
     * corePoolSize:线程池核心线程数
     * maximumPoolSize:线程池最大线程数
     * keepAliveTime:线程池中超过corePoolSize数目的空闲线程最大存活时间,
     *              当调用allowCoreThreadTimeOut()方法时,核心线程中空闲线程也会被关闭
     * TimeUnit:keepAliveTime的单位
     * workQueue:阻塞队列,用来保存等待被执行的任务的阻塞队列，且任务必须实现Runnable接口,常见的有如下队列
     *           ArrayBlockingQueue:基于数组结构的有界阻塞队列，按FIFO排序任务,其内部没有实现读写分离,即生产和消费不能完全并行;
     *           LinkedBlockingQueue:基于链表结构的阻塞队列,按FIFO排序任务,其内部实现了读写分离,可以完全并行的生产和消费,吞吐量通常要高于ArrayBlockingQueue;
     *           SynchronousQueue:一个不存储元素的阻塞队列,每个插入操作必须等到另一个线程调用移除操作,否则插入操作一
     *                            直处于阻塞状态,吞吐量通常要高于LinkedBlockingQueue；
     *           priorityBlockingQueue:具有优先级的无界阻塞队列,优先级的判断通过构造函数传入;
     *           BlockQueue核心方法：
     *           offer(object):如果可能的话直接将object放入到队列,失败直接返回false,不阻塞;
     *           offer(object, TimeOut, TimeUnit):会在指定的时间内将object放入到队列中,失败则直接返回false;
     *           put(object):将object加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续;
     *           poll(Timeout,TimeUnit):指定的时间内取走队列头部的元素,否则直接返回false,不阻塞等待;
     *           take():阻塞等待
     * ThreadFactory:新建线程工厂类
     * RejectedExecutionHandler:线程池的饱和策略,当阻塞队列满了,且没有空闲的工作线程,如果继续提交任务,必须采取一种策略
     *                          处理该任务,JDK线程池默认提供了4种策略：
     *                          AbortPolicy:直接抛出异常,默认策略;
     *                          CallerRunsPolicy:用调用者所在的线程来执行任务;
     *                          DiscardOldestPolicy:丢弃阻塞队列中靠最前的任务,并执行当前任务;
     *                          DiscardPolicy:直接丢弃任务;
     */
    private void initNonBlockThreadPool(){
        this.threadPoolExecutor = new ThreadPoolExecutor(
                10,
                30,
                30,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(10),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler()
        );
    }

    private void initBlockThreadPool(){
        this.threadPoolExecutor = new ThreadPoolExecutor(
                10,
                30,
                30,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(10),
                new CustomThreadFactory(),
                new BlockCustomRejectedExecutionHandler()
        );
    }

    /**
     * 关闭线程池
     */
    public void shutdown(){
        if(null != threadPoolExecutor){
            threadPoolExecutor.shutdown();
        }
    }

    /**
     * 获取非阻塞线程池
     * @return CustomThreadPoolExecutor
     */
    public ThreadPoolExecutor getNonBlockThreadPoolExecutor(){
        this.initNonBlockThreadPool();
        return this.threadPoolExecutor;
    }

    /**
     * 获取阻塞线程池
     * @return CustomThreadPoolExecutor
     */
    public ThreadPoolExecutor getBlockThreadPoolExecutor(){
        this.initBlockThreadPool();
        return this.threadPoolExecutor;
    }

    /**
     * 线程工厂类
     */
    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomThreadPoolExecutor.class.getSimpleName() + " " + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    /**
     * 非阻塞的拒绝策略
     */
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
            // 记录异常,报警处理,持久化保存等策略
            System.out.println("Task " + task.toString() +
                                " rejected from " +
                                executor.toString());
        }
    }

    /**
     * 阻塞的拒绝策略
     */
    private class BlockCustomRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
            try {
                // put方法会阻塞等待,当有来不及处理的任务时,将这些任务阻塞放到队列中,等待队列中的任务被消费
                executor.getQueue().put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
