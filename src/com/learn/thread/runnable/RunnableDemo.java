package com.learn.thread.runnable;

public class RunnableDemo implements Runnable {

    private Thread thread;
    private String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for(int i=4;i>0;i--){
                System.out.println("Thread: " + threadName + "," + i);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + "exiting");
    }


    public void start(){
        System.out.println("Starting " + threadName);
        if(thread == null){
            thread = new Thread(this,threadName);
            thread.start();
        }
    }

    public static void main(String[] args) {
        RunnableDemo r1 = new RunnableDemo("Thread-1");
        r1.start();
        RunnableDemo r2 = new RunnableDemo("Thread-2");
        r2.start();
    }
}
