package com.project.threat.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipInputStream;

public class TestCondition {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition aCondition = lock.newCondition();

    static class A implements Runnable{
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("A要awit了");
                aCondition.await();
                System.out.println("Await结束了");
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    static class B implements Runnable{
        @Override
        public void run() {
            lock.lock();
            aCondition.signal();
            System.out.println("B把A释放了");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new A()).start();
        Thread.sleep(1000);
        new Thread(new B()).start();

    }
}
