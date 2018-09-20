package com.learn.thread;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<String> drop;

    public Consumer(BlockingQueue<String> drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        try {
            String msg = null;
            while (!((msg = drop.take()).equals("DONE")))
                System.out.println(msg);
        } catch (InterruptedException e) {
            System.out.println("Interrupted! Last one but,turn out the lighted.");
        }
    }
}
