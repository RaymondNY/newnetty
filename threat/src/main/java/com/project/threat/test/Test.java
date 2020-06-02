package com.project.threat.test;


import java.util.concurrent.locks.ReentrantLock;

public class Test {

//    volatile int waitStatus //节点状态
//    volatile Node prev //当前节点/线程的前驱节点
//    volatile Node next; //当前节点/线程的后继节点
//    volatile Thread thread;//加入同步队列的线程引用
//    Node nextWaiter;//等待队列中的下一个节点

//    int CANCELLED =  1//节点从同步队列中取消
//    int SIGNAL    = -1//后继节点的线程处于等待状态，如果当前节点释放同步状态会通知后继节点，使得后继节点的线程能够运行；
//    int CONDITION = -2//当前节点进入等待队列中
//    int PROPAGATE = -3//表示下一次共享式同步状态获取将会无条件传播下去
//    int INITIAL = 0;//初始状态
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(11111);
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
            //Thread.sleep(1000000000);
        }
    }
}
