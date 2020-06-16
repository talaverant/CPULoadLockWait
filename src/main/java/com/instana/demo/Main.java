package com.instana.demo;

import java.util.Random;
import static spark.Spark.get;
 
import spark.Request;
import spark.Response;
import spark.Route;

public class Main {
    private static Object lock = new Object();

    public static void main(String[] args) {
      get("/hello", (req, res) -> {
        simulateCPULoad(200);

        Thread t = new Thread(new Runnable() {
            public void run() {
              try {
                synchronized(lock) {
                    Thread.sleep(80);
                }
              }
              catch(Exception ex) {
                ex.printStackTrace();
              }
            }
        });
        t.start();

        simulateLockWait();

        t.join();

        return  "hello";          
      });
    }

    public static void simulateCPULoad(int usage) throws Exception {
      Random rand = new Random();
      for (int i = 0; i < usage * 1000000; i++) {
        rand.nextInt(100000);
      }
    }

    public static void simulateLockWait() throws Exception {
      Thread.sleep(10);
      synchronized(lock) {
        Thread.sleep(10);
      }
    }
}
