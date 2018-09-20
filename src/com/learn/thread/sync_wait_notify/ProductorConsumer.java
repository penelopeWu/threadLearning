package com.learn.thread.sync_wait_notify;

public class ProductorConsumer {

    private static final Integer MAX_PRODUCT = 100;
    private static final Integer MIN_PRODUCT = 0;
    private Integer product;

    public synchronized void produce(){

        if(this.product>= MAX_PRODUCT){
            try {
                wait();
                System.out.println("产品已满，请稍后再生产...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        this.product++;
        System.out.println("生产者生产第 " + this.product + " 个产品...");
        notifyAll();//通知等待区的消费者，可以消费产品了
    }


    public synchronized void consume(){
        if(this.product <= MIN_PRODUCT){
            try {
                wait();
                System.out.println("缺货，请稍后再取...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        System.out.println("消费者取走了第 " + this.product + " 个产品");
        this.product --;
        notifyAll();//通知等待区的生产者可以去生产产品了。
    }
}
