package com.jylu.study.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * Description: Zookeeper会话实例
 * User: lujiayun
 * Date: 2017-11-15
 * Time: 22:08
 */
public class ZookeeperSimple implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        String connectString = "192.168.2.111:2181,192.168.2.112:2181,192.168.2.113:2181";
        try {
            // 创建zookeeper连接
            final ZooKeeper zooKeeper = new ZooKeeper(connectString, 500, new ZookeeperSimple());
            // 阻塞等待
            countDownLatch.await();
            System.out.println(zooKeeper.getState());
            // 创建永久路径,并写入数据
            String createPath = "/zk-test";
            zooKeeper.create(createPath, "lujiayun".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("Create znode successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
