package com.learn.thread.thread;

public class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;

    public ThreadDemo(String name) {
        super(name);
        this.threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try{
            for(int i=4;i>0;i--){
                System.out.println("Thread: " + threadName + "," + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted ");
        }
        System.out.println("Thread " + threadName + " exiting ");
    }

    @Override
    public synchronized void start() {
        System.out.println("Starting " + threadName);
        if(t == null){
            t= new Thread(this,threadName);
            t.start();
        }
    }

    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo("Thread-1");
        t1.start();

        ThreadDemo t2 = new ThreadDemo("Thread-2");
        t2.start();
    }
}
