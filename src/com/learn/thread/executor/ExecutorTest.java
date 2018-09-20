package com.learn.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorTest {

    private static Integer pages = 1;//网页数

    private static boolean exeFlag = true; //执行标识

    public static void main(String[] args) {

        //创建ExecutorService，连接池默认连接10个。
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        while (exeFlag) {
            if (pages <= 100) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("爬取了第" + pages + "个网页...");
                        pages++;
                    }
                });
            }else {
                if (((ThreadPoolExecutor)executorService).getActiveCount() == 0){//没有活动线程
                    executorService.shutdown();
                    exeFlag = false;
                    System.out.println("爬虫任务已经完成...");
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
